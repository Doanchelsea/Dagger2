package com.example.dagger2_api_login.ui.main.presenter;

import android.util.Log;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.ui.main.contract.MainContract;
import com.example.dagger2_api_login.untils.StringUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

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
    public void getTripPackge() {

        Token token = dataManager.getToken();
        if (token == null) {
            return;
        }
        String tokenKey = token.getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            mView.showError(R.string.common_noti_error_message);
            return;
        }

        Disposable disposable = dataManager.getLastStatus(tokenKey)
                .flatMap(history -> Observable.just(history.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultsHis -> {
                    mView.showTripPackge(resultsHis);
                }, throwable -> {
                    mView.showError(R.string.common_noti_error);
                });

        addSubscribe(disposable);

    }


}
