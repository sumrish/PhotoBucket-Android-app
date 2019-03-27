package com.example.userphotobucket.ui.user;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.userphotobucket.R;
import com.example.userphotobucket.base.BaseActivity;
import com.example.userphotobucket.databinding.ActivityUserBinding;
import com.example.userphotobucket.interfaces.IOnItemClick;
import com.example.userphotobucket.ui.album.ActivityAlbum;

public class UserActivity extends BaseActivity<ActivityUserBinding, UserViewModel> implements IOnItemClick {

    private UserViewModel mUserViewModel;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public UserViewModel getViewModel() {
        mUserViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        return mUserViewModel;    }

    @Override
    protected void initUi(Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewDataBinding.user.setLayoutManager(layoutManager);
        mViewDataBinding.user.setAdapter(mUserViewModel.getUserAdapter());
        mUserViewModel.getUserAdapter().setInterface(this);
        mUserViewModel.getAllUser();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        return intent;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = ActivityAlbum.newIntent(UserActivity.this);
        intent.putExtra("AlbumId", position);
        startActivity(intent);
//        Bundle args = new Bundle();
//        args.putInt("AlbumId", position);
//        ActivityAlbum fragment = ActivityAlbum.newInstance();
//        fragment.setArguments(args);
//        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, "Notification")./*addToBackStack(Integer.toString(getFragmentCount())).*/commit();

    }

    @Override
    public void onItemClick(int position, float id) {

    }

    @Override
    public void onItemClick(int position, String url) {

    }
}
