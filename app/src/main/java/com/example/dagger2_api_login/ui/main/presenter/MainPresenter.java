package com.example.dagger2_api_login.ui.main.presenter;

import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.ui.main.contract.MainContract;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View>
        implements MainContract.Presenter<MainContract.View> {

    public DataManager dataManager;

    @Inject
     MainPresenter(DataManager manager) {
        this.dataManager = manager;
    }

    @Override
    public void ShowUserInfoPresenter() {
        UserInfo userInfo = dataManager.getUserInfo();
        mView.showUserInfo(userInfo);
    }

    @Override
    public void SingUp() {
        dataManager.clearAllUserInfo();
    }

}
