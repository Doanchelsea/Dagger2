package com.example.dagger2_api_login.dagger.components;


import com.example.dagger2_api_login.dagger.moduls.ActivityModule;
import com.example.dagger2_api_login.dagger.scope.ActivityScope;
import com.example.dagger2_api_login.ui.account.fragment.AccountFragment;
import com.example.dagger2_api_login.ui.history.fragment.HistoryFragment;
import com.example.dagger2_api_login.ui.home.fragment.HomeFragment;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;
import com.example.dagger2_api_login.ui.main.activity.MainActivity;
import com.example.dagger2_api_login.ui.splash.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponets.class}, modules = {ActivityModule.class})
public interface ActivityComponets {

    LoginActivity inject(LoginActivity loginActivity);

    SplashActivity inject(SplashActivity splashActivity);

    MainActivity inject(MainActivity mainActivity);

    HomeFragment inject(HomeFragment homeFragment);

    HistoryFragment inject(HistoryFragment historyFragment);

    AccountFragment inject(AccountFragment accountFragment);


}
