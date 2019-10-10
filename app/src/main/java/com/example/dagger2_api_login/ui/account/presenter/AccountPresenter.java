package com.example.dagger2_api_login.ui.account.presenter;

import android.widget.Button;

import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.ui.account.contract.AccountContract;
import com.jakewharton.rxbinding3.view.RxView;

import javax.inject.Inject;

public class AccountPresenter extends RxPresenter<AccountContract.View> implements AccountContract.Presenter<AccountContract.View>  {

    public DataManager dataManager;

    @Inject
    AccountPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getAvatar() {
        mView.showAvatar(dataManager.getUserInfo().getAvatar());
    }

    @Override
    public void getName() {
        mView.showName(dataManager.getUserInfo().getFullName());
    }

    @Override
    public void Logout() {
        dataManager.clearAllUserInfo();
    }

}
