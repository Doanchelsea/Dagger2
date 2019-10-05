package com.example.dagger2_api_login.ui.splash.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.UserInfo;

public interface SplashContract {

    interface View extends BaseContract.BaseView {

        void showLogin();

        void showMain();


    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void saveDeviceIdSharedPreferences();
    }
}
