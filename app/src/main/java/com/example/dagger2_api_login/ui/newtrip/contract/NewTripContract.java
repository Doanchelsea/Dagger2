package com.example.dagger2_api_login.ui.newtrip.contract;

import android.view.inputmethod.InputMethodManager;

import com.example.dagger2_api_login.base.BaseContract;
import com.example.dagger2_api_login.model.historyDetail.Result;
import com.example.dagger2_api_login.model.historyDetail.Results;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public interface NewTripContract {

    interface View extends BaseContract.BaseView {
        void ShowLatLng(double lat,double lng,String name);
        void error(int error);
        void show(List<Result> result);

    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void SeachBar(MaterialSearchBar materialSearchBar,
                      List<AutocompletePrediction> predictionList,
                      PlacesClient mplacesClient,
                      InputMethodManager inm);

        void showResult(double lat,double lng,double locationLat, double locationLng);
    }
}
