package com.example.dagger2_api_login.dagger.components;

import android.app.Application;
import android.content.Context;

import com.example.dagger2_api_login.ApplicationDagger;
import com.example.dagger2_api_login.dagger.moduls.ApplicationModuls;
import com.example.dagger2_api_login.dagger.qualifierts.ApplicationContext;
import com.example.dagger2_api_login.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModuls.class)
public interface ApplicationComponets {

    void inject(ApplicationDagger application);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
