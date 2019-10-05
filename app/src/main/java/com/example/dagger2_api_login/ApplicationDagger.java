package com.example.dagger2_api_login;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.dagger2_api_login.dagger.components.ApplicationComponets;
import com.example.dagger2_api_login.dagger.components.DaggerApplicationComponets;
import com.example.dagger2_api_login.dagger.moduls.ApplicationModuls;

public class ApplicationDagger extends Application {

    private ApplicationComponets applicationComponent;

    public static ApplicationDagger getInstance(Context context){
        return (ApplicationDagger) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponets.builder()
                .applicationModuls(new ApplicationModuls(this))
                .build();
        applicationComponent.inject(this);

    }

    public ApplicationComponets getComponent() {
        return applicationComponent;
    }
}
