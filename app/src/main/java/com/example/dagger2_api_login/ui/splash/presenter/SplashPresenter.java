package com.example.dagger2_api_login.ui.splash.presenter;

import android.util.Log;

import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.ui.splash.contract.SplashContract;
import com.example.dagger2_api_login.untils.ErrorHandler;
import com.example.dagger2_api_login.untils.StringUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter extends RxPresenter<SplashContract.View>
        implements SplashContract.Presenter<SplashContract.View> {

    public DataManager dataManager;

    @Inject
    SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void saveDeviceIdSharedPreferences() {

//        mView.showPhoneNumber(userInfo);
        Token token = dataManager.getToken();
        if (token == null) {
            mView.showLogin();
            return;
        }

        String tokenKey = token.getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            mView.showLogin();
            return;
        }
        Disposable disposable = dataManager.getLastStatusDriver(tokenKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showMain();
                        },
                        (error) -> {
                            Log.d("dâd",""+error);
                            mView.showErrorLastStatus(ErrorHandler.errorParser(error),dataManager);
                        }
                );
        addSubscribe(disposable);
    }

    @Override
    public void getDevieId() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Logger.w("[GET DEVICE ID ERROR: %s]", task.getException());
                        return;
                    }
                    if (task.getResult() != null) {
                        String deviceId = task.getResult().getToken();
                        dataManager.setDeviceId(deviceId);
                        mView.showDevieID();
                    }
                });
    }
}
