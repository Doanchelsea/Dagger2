package com.example.dagger2_api_login.ui.splash.presenter;

import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.ui.splash.contract.SplashContract;

import javax.inject.Inject;

public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter<SplashContract.View> {

    public DataManager dataManager;

    @Inject
    SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void saveDeviceIdSharedPreferences() {
        UserInfo userInfo = dataManager.getUserInfo();

//        mView.showPhoneNumber(userInfo);
        if (userInfo == null){
            mView.showLogin();
            return;
        } else {
            mView.showMain();
            return;
        }
    }
}
