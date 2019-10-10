package com.example.dagger2_api_login.data.local;

import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.dagger.UserInfo;

public interface PreferencesHelper {

    void setLoggedIn(boolean isLoggedIn);
    boolean IsLoggedIn();

    void setUserInfo(UserInfo userInfo);
    
    UserInfo getUserInfo();

    void clearUserInfo();


    void setToken(Token token);

    Token getToken();

    void clearToken();


}
