package com.example.dagger2_api_login.ui.history.contract;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.model.history.ResultsHis;
import com.example.dagger2_api_login.model.historyDetail.HistoryDetail;
import com.example.dagger2_api_login.model.historyDetail.Results;

public interface DetailContractHistory  {

    interface View extends BaseContract.BaseView{

        void showResult(Results results);

        void showRattingBar(Results results);


    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{

        void getHistoryTripDetailDriver(String tripPackage);

        void getRattingBar(String tripPackage,String ratingTrip);

    }
}
