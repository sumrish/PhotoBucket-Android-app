package com.example.userphotobucket.ui.photo;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.userphotobucket.R;
import com.example.userphotobucket.base.BaseActivity;
import com.example.userphotobucket.databinding.ActivityPhotoBinding;
import com.example.userphotobucket.databinding.DialogePhotoBinding;
import com.example.userphotobucket.interfaces.IOnItemClick;
import com.example.userphotobucket.utils.PicassoUtils;


public class ActivityPhoto extends BaseActivity<ActivityPhotoBinding,PhotoViewModel> implements IOnItemClick{

    private PhotoViewModel mPhotoViewModel;
    private Dialog mDialog;
    private DialogePhotoBinding mDialogePhoto;
    ScaleGestureDetector scaleGDetector;
    float scale=1f;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    public PhotoViewModel getViewModel() {
        mPhotoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(PhotoViewModel.class);
        return mPhotoViewModel;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ActivityPhoto.class);
        return intent;
    }

    @Override
    protected void initUi(Bundle savedInstanceState) {
        int id=getIntent().getIntExtra("AlbumId", 0);
        mViewDataBinding.photos.setLayoutManager(new GridLayoutManager(this, 3));
        mViewDataBinding.photos.setAdapter(mPhotoViewModel.getPhotoViewModel());
        mPhotoViewModel.getPhotoViewModel().setInterface(this);
        mPhotoViewModel.getAllPhotos(id);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClick(int position, float id) {

    }

    @Override
    public void onItemClick(int position, String url) {
        mDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        mDialogePhoto= DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialoge_photo, null, false);
        mDialog.setContentView(mDialogePhoto.getRoot());
        scaleGDetector =new ScaleGestureDetector(this, new ScaleListener());

        PicassoUtils.picassoLoadUrlUsingListTag(  mDialogePhoto.photo, url,"thumbnail",R.drawable.ic_launcher_foreground,0,false);
        mDialogePhoto.photo.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                scaleGDetector.onTouchEvent(event);
                return true;
            }
        });

        mDialog.show();

    }


    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener{

        public boolean onScaleBegin(ScaleGestureDetector sgd){
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector sgd){
        }

        public boolean onScale(ScaleGestureDetector sgd){
            scale*= sgd.getScaleFactor();
            mDialogePhoto.photo.setScaleX(scale);
            mDialogePhoto.photo.setScaleY(scale);
            return true;

        }

    }

}
