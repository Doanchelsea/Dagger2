package com.example.dagger2_api_login.ui.main.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.model.dagger.UserInfo;

public interface MainContract {

    interface View extends BaseContract.BaseView {

        void showUserInfo(UserInfo userInfo);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void ShowUserInfoPresenter();

        void SingUp();
    }
}
