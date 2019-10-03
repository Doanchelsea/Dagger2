package com.example.dagger2_api_login.dagger.moduls;

import android.app.Activity;

import com.example.dagger2_api_login.dagger.qualifierts.ActivityContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * doannd
 *
 * activity module
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    @ActivityContext
    Activity provideActivity() {
        return activity;
    }

}
