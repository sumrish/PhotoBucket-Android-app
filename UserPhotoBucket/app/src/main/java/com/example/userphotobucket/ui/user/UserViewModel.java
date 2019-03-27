package com.example.userphotobucket.ui.user;

import android.util.Log;

import com.example.userphotobucket.base.BaseViewModel;
import com.example.userphotobucket.ui.user.adapter.UserAdapter;
import com.example.userphotobucket.ui.user.model.User;
import com.example.userphotobucket.utils.S;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class UserViewModel extends BaseViewModel {

    private UserAdapter mUserAdapter;
    private List<User> mUser= new ArrayList<>();

    public UserViewModel(){
        super();
        mUserAdapter = new UserAdapter();
    }

    public UserAdapter getUserAdapter() {
        return mUserAdapter;
    }

    public void getAllUser(){
        getCompositeDisposable().add(getApiClient().getAllUsers().subscribeOn(S.io()).observeOn(S.ui()).subscribeWith(new DisposableObserver<Response<List<User>>>() {
            @Override
            public void onNext(Response<List<User>> dataResponse) {
                if(dataResponse.body().size()>0) {

                    for (int i = 0; i < dataResponse.body().size(); i++) {
                        User user = new User();
                        user.setName(dataResponse.body().get(i).getName());
                        user.setId(dataResponse.body().get(i).getId());
                        user.setEmail(dataResponse.body().get(i).getEmail());
                        user.setAddress(dataResponse.body().get(i).getAddress());
                        mUser.add(user);
                    }
                    mUserAdapter.setDataset(mUser);
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
