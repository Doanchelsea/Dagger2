package com.example.dagger2_api_login.ui.home.presenter;

import android.location.Location;
import android.util.Log;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.ui.home.contract.HomeContract;
import com.example.dagger2_api_login.untils.ErrorHandler;
import com.example.dagger2_api_login.untils.StringUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HomePresenter extends RxPresenter<HomeContract.View>
        implements HomeContract.Presenter<HomeContract.View> {


    DataManager dataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void findTripByLocation(String lat, String lng) {

        Token token = dataManager.getToken();

        if (token == null) {
            mView.showError(R.string.common_noti_error);
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            mView.showError(R.string.common_noti_error);
            return;
        }

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put("latitude",lat);
        httpBody.put("longitude",lng);


        Map<String,Object> hasmap = new HashMap<>();
        hasmap.put("startlocation",httpBody);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(hasmap)).toString());


        Disposable disposable = dataManager.findTripByLocation(tokenKey,body)
                .flatMap(historyDetail -> Observable.just(historyDetail.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    mView.showSuccer();
                },throwable -> {
                    mView.ShowErrorFind(ErrorHandler.errorParser(throwable),dataManager);
                });
        addSubscribe(disposable);
    }
}
