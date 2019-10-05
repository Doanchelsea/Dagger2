package com.example.dagger2_api_login.ui.login.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Results;

public interface LoginContract {

    interface View extends BaseContract.BaseView {

        void onResult(Results results);


    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void checkExistDriverServer(String phoneNumberAuthenticated);

        void saveUserInfoSharedPreferences(Results results);
    }

}
