package com.example.dagger2_api_login.data;

import com.example.dagger2_api_login.api.PostService;

import com.example.dagger2_api_login.data.local.PreferencesHelper;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.model.history.History;

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
    public Observable<Dagger> getLastStatusDriver(String tokenKey) {
        return postService.getLastStatusDriver(tokenKey);
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
    }
}
