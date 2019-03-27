package com.example.userphotobucket;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;

import com.example.userphotobucket.base.BaseActivity;
import com.example.userphotobucket.databinding.ActivitySplashBinding;
import com.example.userphotobucket.ui.splash.SplashViewModel;
import com.example.userphotobucket.ui.user.UserActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

//    ViewModelProvider.Factory viewModelFactory;

    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    protected void initUi(Bundle savedInstanceState) {
        Intent intent = UserActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
}
