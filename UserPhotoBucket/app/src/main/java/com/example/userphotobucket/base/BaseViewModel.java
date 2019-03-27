package com.example.userphotobucket.base;

import android.arch.lifecycle.ViewModel;
import com.example.userphotobucket.api.ApiClient;
import com.example.userphotobucket.api.IApis;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private IApis mApiService;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public IApis getApiClient() {
       return mApiService = ApiClient.getClient().create(IApis.class);
    }
}
