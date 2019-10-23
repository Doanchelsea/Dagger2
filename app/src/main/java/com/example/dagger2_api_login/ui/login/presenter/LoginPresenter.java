package com.example.dagger2_api_login.ui.login.presenter;

import android.content.Context;
import android.util.Log;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.dagger.qualifierts.ApplicationContext;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.ui.login.contract.LoginContract;
import com.example.dagger2_api_login.untils.StringUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class LoginPresenter extends RxPresenter<LoginContract.View>
        implements LoginContract.Presenter<LoginContract.View> {
    private static final boolean DRIVER_LOGGED_IN = true;
    private DataManager dataManager;
    private String deviceId = "";

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void checkExistDriverServer(String phoneNumberAuthenticated) {
        Map<String, String> httpBody = new HashMap<>();

        if (StringUtils.isEmpty(phoneNumberAuthenticated)) {
            mView.showError(R.string.common_noti_error_message);
            return;
        }

        deviceId = FirebaseInstanceId.getInstance().getToken();
        if (deviceId == null) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Get new Instance ID token
                            deviceId = Objects.requireNonNull(task.getResult()).getToken(); }
                    })
                    .addOnFailureListener(e -> {
                    });
        }

        httpBody.put("stateCode", "84");
        httpBody.put("deviceType", "1");
        httpBody.put("deviceId",deviceId);
        httpBody.put("phoneNumber", phoneNumberAuthenticated);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

        Disposable disposable = dataManager.login(body)
                .flatMap(loginJsonResult -> Observable.just(loginJsonResult.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (results) -> {
                            mView.onResult(results);
                        },
                        (error) -> {

                            mView.showError(R.string.common_noti_error_message);
                        }
                );

        addSubscribe(disposable);
    }

    @Override
    public void saveUserInfoSharedPreferences(Results results) {
        if (results == null) {
            mView.showError(R.string.common_noti_error_message);
            return;
        }
        dataManager.updateUserInfoSharedPreference(results.getUserInfo(),results.getToken(), DRIVER_LOGGED_IN);
        mView.showMain();
    }
}
