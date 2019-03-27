package com.example.userphotobucket.ui.album;

import android.util.Log;

import com.example.userphotobucket.base.BaseViewModel;
import com.example.userphotobucket.ui.album.adapter.AlbumAdapter;
import com.example.userphotobucket.ui.album.model.Album;
import com.example.userphotobucket.ui.photo.model.Photo;
import com.example.userphotobucket.utils.S;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class AlbumViewModel extends BaseViewModel {

    private AlbumAdapter mAlbumAdapter;
    private List<Album> mUserAlbum;
    private HashMap<Float, Photo> mPhoto;

    public AlbumViewModel(){
        super();
        mAlbumAdapter = new AlbumAdapter();
    }

    public AlbumAdapter getAlbumAdapter() {
        return mAlbumAdapter;
    }

    public void getAllAlbumsList(int position){
        Log.d("UserActivity id: ", position+" ");
        mUserAlbum= new ArrayList<>();
        mPhoto= new HashMap<>();
        getCompositeDisposable().add(getApiClient().getAllUserAlbums(position).subscribeOn(S.io()).observeOn(S.ui()).subscribeWith(new DisposableObserver<Response<List<Album>>>() {
            @Override
            public void onNext(Response<List<Album>> dataResponse) {
                Log.d("UserActivity Albums", dataResponse.body().toString());

                for(int i=0; i< dataResponse.body().size(); i++) {
                    Album album = new Album();
                    album.setId(dataResponse.body().get(i).getId());
                    album.setTitle(dataResponse.body().get(i).getTitle());
                    album.setUserId(dataResponse.body().get(i).getUserId());  //TODO: Not needed
                    mUserAlbum.add(album);

                    getCompositeDisposable().add(getApiClient().getAllPhotos((int)album.getId()).subscribeOn(S.io()).observeOn(S.ui()).subscribeWith(new DisposableObserver<Response<List<Photo>>>() {
                        @Override
                        public void onNext(Response<List<Photo>> dataResponse) {
                            Log.d("UserActivity Photos: ", dataResponse.body().get(0).getAlbumId()+"");
                            Photo photo = new Photo();
                            photo.setId(dataResponse.body().get(0).getId());
                            photo.setAlbumId(dataResponse.body().get(0).getAlbumId());
                            photo.setTitle(dataResponse.body().get(0).getTitle());
                            photo.setThumbnailUrl(dataResponse.body().get(0).getThumbnailUrl());
                            photo.setUrl(dataResponse.body().get(0).getUrl());
//                            user.setThumbnail(dataResponse.body().get(0).getThumbnailUrl());
                            mPhoto.put(photo.getAlbumId(), photo);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("UserActivity", e.toString());

                        }

                        @Override
                        public void onComplete() {
                            Log.d("Size of Photo List: ", mPhoto.size() + "");
                            Log.d("Size of Album List: ", mUserAlbum.size() + "");
                            if(mPhoto.size() == mUserAlbum.size() ){

                                for(int i=0 ; i<mPhoto.size(); i++){
                                    if( mPhoto.containsKey(mUserAlbum.get(i).getId())) {
//                                    int id=(int)mPhoto.get(i).getAlbumId();
                                        mUserAlbum.get(i).setThumbnail(mPhoto.get(mUserAlbum.get(i).getId()).getThumbnailUrl());
                                    }
                                }
                                mAlbumAdapter.setDataset(mUserAlbum);

                                Log.d("Size of Album List: ", mUserAlbum.size() + "");

                            }
                        }
                    }));
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("UserActivity", e.toString());

            }

            @Override
            public void onComplete() {

            }
        }));
    }

}

