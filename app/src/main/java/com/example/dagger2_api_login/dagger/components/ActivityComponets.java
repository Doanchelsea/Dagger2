package com.example.dagger2_api_login.dagger.components;


import com.example.dagger2_api_login.dagger.moduls.ActivityModule;
import com.example.dagger2_api_login.dagger.scope.ActivityScope;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponets.class}, modules = {ActivityModule.class})
public interface ActivityComponets {

    LoginActivity inject(LoginActivity loginActivity);

}
