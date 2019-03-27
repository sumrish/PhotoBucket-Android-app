package com.example.userphotobucket.ui.album;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.userphotobucket.R;
import com.example.userphotobucket.base.BaseActivity;
import com.example.userphotobucket.databinding.ActivityAlbumBinding;
import com.example.userphotobucket.interfaces.IOnItemClick;
import com.example.userphotobucket.ui.photo.ActivityPhoto;
import com.example.userphotobucket.ui.user.UserActivity;


public class ActivityAlbum extends BaseActivity<ActivityAlbumBinding,AlbumViewModel> implements IOnItemClick {

    private AlbumViewModel mAlbumViewModel;


    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    public AlbumViewModel getViewModel() {
        mAlbumViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AlbumViewModel.class);
        return mAlbumViewModel;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ActivityAlbum.class);
        return intent;
    }

    @Override
    protected void initUi(Bundle savedInstanceState) {
        int id=getIntent().getIntExtra("AlbumId", 0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewDataBinding.album.setLayoutManager(layoutManager);
//        mViewDataBinding.album.setLayoutManager(new GridLayoutManager(this, 3));
        mViewDataBinding.album.setAdapter(mAlbumViewModel.getAlbumAdapter());
        mAlbumViewModel.getAlbumAdapter().setInterface(this);
        mAlbumViewModel.getAllAlbumsList(id);
    }

//    @Override
//    protected void setUp() {
//        Bundle b = getArguments();
//        int id = b.getInt("AlbumId");
//        mViewDataBinding.album.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        mViewDataBinding.album.setAdapter(mAlbumViewModel.getAlbumAdapter());
//        mAlbumViewModel.getAlbumAdapter().setInterface(this);
//        mAlbumViewModel.getAlbumAdapter().setContext(getActivity());
//        mAlbumViewModel.getAllAlbumsList(id);
//    }

//    public static ActivityAlbum newInstance(){
//        return new ActivityAlbum();
//    }

    @Override
    public void onItemClick(int position) {


    }

    @Override
    public void onItemClick(int position, float id) {
        Intent intent = ActivityPhoto.newIntent(ActivityAlbum.this);
        intent.putExtra("AlbumId", position);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, String url) {

    }
}
