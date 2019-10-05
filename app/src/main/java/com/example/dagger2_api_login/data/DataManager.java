package com.example.dagger2_api_login.data;

import com.example.dagger2_api_login.api.PostService;

import com.example.dagger2_api_login.data.local.PreferencesHelper;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.UserInfo;

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

    public void updateUserInfoSharedPreference(UserInfo userInfo, boolean isLoggedIn) {
        setUserInfo(userInfo);
        setLoggedIn(isLoggedIn);
    }


    public void clearAllUserInfo() {
        clearUserInfo();
    }
}
