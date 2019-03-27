package com.example.userphotobucket.ui.photo;

import android.util.Log;

import com.example.userphotobucket.base.BaseViewModel;
import com.example.userphotobucket.ui.photo.adapter.PhotoAdpater;
import com.example.userphotobucket.ui.photo.model.Photo;
import com.example.userphotobucket.utils.S;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class PhotoViewModel extends BaseViewModel {

    private PhotoAdpater mPhotoAdpater;
    private List<Photo> mPhoto;

    public PhotoViewModel() {
        super();
        mPhotoAdpater = new PhotoAdpater();
    }

    public PhotoAdpater getPhotoViewModel() {
        return mPhotoAdpater;
    }

    public void getAllPhotos(int position) {
        mPhoto=new ArrayList<>();

        getCompositeDisposable().add(getApiClient().getAllPhotos(position).subscribeOn(S.io()).observeOn(S.ui()).subscribeWith(new DisposableObserver<Response<List<Photo>>>() {
            @Override
            public void onNext(Response<List<Photo>> dataResponse) {
                for(int i=0; i< dataResponse.body().size(); i++) {
                    Photo photo = new Photo();
                    photo.setId(dataResponse.body().get(i).getId());
                    photo.setAlbumId(dataResponse.body().get(i).getAlbumId());
                    photo.setTitle(dataResponse.body().get(i).getTitle());
                    photo.setThumbnailUrl(dataResponse.body().get(i).getThumbnailUrl());
                    photo.setUrl(dataResponse.body().get(i).getUrl());
                    mPhoto.add( photo);
                }
                mPhotoAdpater.setDataset(mPhoto);
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
