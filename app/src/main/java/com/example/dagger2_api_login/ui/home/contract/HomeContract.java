package com.example.dagger2_api_login.ui.home.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.error.Error;

public interface HomeContract {
    interface View extends BaseContract.BaseView{
        void showSuccer();

        void ShowErrorFind(Error error,DataManager manager);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void findTripByLocation(String lat, String lng);
    }
}
