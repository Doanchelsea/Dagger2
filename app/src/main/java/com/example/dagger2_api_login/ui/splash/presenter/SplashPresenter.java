package com.example.dagger2_api_login.ui.splash.presenter;

import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.ui.splash.contract.SplashContract;
import com.example.dagger2_api_login.untils.StringUtils;
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
                            Logger.json(new Gson().toJson(beans));
                            mView.showMain();
                        },
                        (error) -> {
                            Logger.w("GET LAST STATUS ERROR: %s", error);
//                            mView.showError(R.string.common_noti_error_message);
                        }
                );

        addSubscribe(disposable);
    }
}
