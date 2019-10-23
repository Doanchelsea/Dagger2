package com.example.dagger2_api_login.data;

import com.example.dagger2_api_login.api.PostService;

import com.example.dagger2_api_login.data.local.PreferencesHelper;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.model.error.Error;
import com.example.dagger2_api_login.model.history.History;
import com.example.dagger2_api_login.model.historyDetail.HistoryDetail;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class DataManager implements PostService, PreferencesHelper {

    private PostService postService;
    private PreferencesHelper preferencesHelper;



    public DataManager(PostService postService,PreferencesHelper preferencesHelper) {
        this.postService = postService;
        this.preferencesHelper =preferencesHelper;
    }

    @Override
    public Observable<Dagger> login(RequestBody body) {
        return postService.login(body);
    }

    @Override
    public Observable<History> history(String tokenKey, RequestBody body) {
        return postService.history(tokenKey,body);
    }

    @Override
    public Observable<History> getLastStatusDriver(String tokenKey) {
        return postService.getLastStatusDriver(tokenKey);
    }

    @Override
    public Observable<HistoryDetail> historyDetail(String tokenKey, RequestBody body) {
        return postService.historyDetail(tokenKey,body);
    }

    @Override
    public Observable<HistoryDetail> rattingBarTrip(String tokenKey, RequestBody body) {
        return postService.rattingBarTrip(tokenKey,body );
    }

    @Override
    public Observable<Error> findTripByLocation(String tokenKey, RequestBody body) {
        return postService.findTripByLocation(tokenKey,body);
    }

    @Override
    public Observable<HistoryDetail> getLastStatus(String tokenKey) {
        return postService.getLastStatus(tokenKey);
    }

    @Override
    public Observable<HistoryDetail> getLstCarType(String tokenKey, RequestBody body) {
        return postService.getLstCarType(tokenKey,body);
    }


    /*----------------------------------Prefs Helper----------------------------------*/
    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        preferencesHelper.setLoggedIn(isLoggedIn);
    }

    @Override
    public boolean IsLoggedIn() {
        return preferencesHelper.IsLoggedIn();
    }

    @Override
    public void setDeviceId(String deviceId) {
        preferencesHelper.setDeviceId(deviceId);
    }

    @Override
    public String getDeviceId() {
        return preferencesHelper.getDeviceId();
    }

    @Override
    public void clearDeviceId() {
        preferencesHelper.clearDeviceId();
    }


    // UserInfo
    @Override
    public void setUserInfo(UserInfo userInfo) {
        preferencesHelper.setUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfo() {
        return preferencesHelper.getUserInfo();
    }

    @Override
    public void clearUserInfo() {
        preferencesHelper.clearUserInfo();
    }

    // Token
    @Override
    public void setToken(Token token) {
        preferencesHelper.setToken(token);
    }

    @Override
    public Token getToken() {
        return preferencesHelper.getToken();
    }

    @Override
    public void clearToken() {
        preferencesHelper.clearToken();
    }


    public void updateUserInfoSharedPreference(UserInfo userInfo,Token token, boolean isLoggedIn) {
        setUserInfo(userInfo);
        setToken(token);
        setLoggedIn(isLoggedIn);
    }

    public void clearAllUserInfo() {
        clearUserInfo();
        clearToken();
        clearDeviceId();
    }
}
