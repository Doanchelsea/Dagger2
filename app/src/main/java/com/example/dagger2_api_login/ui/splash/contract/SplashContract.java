package com.example.dagger2_api_login.ui.splash.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.model.error.Error;

public interface SplashContract {

    interface View extends BaseContract.BaseView {

        void showLogin();

        void showMain();

        void showErrorLastStatus(Error error, DataManager dataManager);

        void showDevieID();


    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void saveDeviceIdSharedPreferences();

        void getDevieId();
    }
}
