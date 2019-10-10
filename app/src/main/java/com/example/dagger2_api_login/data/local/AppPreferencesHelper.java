package com.example.dagger2_api_login.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dagger2_api_login.dagger.qualifierts.ApplicationContext;
import com.example.dagger2_api_login.dagger.qualifierts.PreferenceInfo;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO";
    private static final String PREF_KEY_IS_LOGGED_IN = "PREF_KEY_IS_LOGGED_IN ";
    private static final String PREF_KEY_TOKEN_INFO = "PREF_KEY_TOKEN_INFO";

    private SharedPreferences mPrefs;

    @Inject
    AppPreferencesHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    @Override
    public boolean IsLoggedIn() {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGGED_IN, false);
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        mPrefs.edit().putString(PREF_KEY_USER_INFO, new Gson().toJson(userInfo)).apply();
    }

    @Override
    public UserInfo getUserInfo() {
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_USER_INFO, ""), UserInfo.class);
    }

    @Override
    public void clearUserInfo() {
        mPrefs.edit().remove(PREF_KEY_USER_INFO).apply();
    }



    @Override
    public void setToken(Token token) {
        mPrefs.edit().putString(PREF_KEY_TOKEN_INFO, new Gson().toJson(token)).apply();
    }

    @Override
    public Token getToken() {
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_TOKEN_INFO, ""), Token.class);
    }

    @Override
    public void clearToken() {
        mPrefs.edit().remove(PREF_KEY_TOKEN_INFO).apply();
    }
}
