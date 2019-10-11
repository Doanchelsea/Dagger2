package com.example.dagger2_api_login.ui.history.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.model.history.TripSection;
import com.example.dagger2_api_login.ui.history.adapter.HistoryAdapter;

import java.util.List;

public interface ContractHistory {

    interface View extends BaseContract.BaseView {
        void showHistoryTripForDriver(List<TripSection> tripSectionList);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getHistoryTripForDriver(String page, String count);
    }

}
