package com.example.dagger2_api_login.ui.account.contract;

import android.widget.Button;

import com.example.dagger2_api_login.base.BaseContract;

public interface AccountContract {

    interface View extends BaseContract.BaseView{
        void showAvatar(String url);
        void showName(String name);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getAvatar();
        void getName();
        void Logout();
    }

}
