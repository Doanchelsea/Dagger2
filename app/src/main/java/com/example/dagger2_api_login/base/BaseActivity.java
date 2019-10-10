package com.example.dagger2_api_login.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dagger2_api_login.ApplicationDagger;
import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.dagger.components.ActivityComponets;
import com.example.dagger2_api_login.dagger.components.DaggerActivityComponets;
import com.example.dagger2_api_login.dagger.moduls.ActivityModule;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends RxAppCompatActivity {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected Context context;
    protected Merlin merlin;
    private Unbinder unbinder;
    private ActivityComponets activityComponets;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        context = this;
        setupComponent();
        attachView();
        addEvents();
        merlin = initMerlin();
    }

    protected abstract int getLayoutId();

    @Override
    protected void onStart() {
        super.onStart();
        merlin.bind();
    }

    @Override
    protected void onStop() {
        super.onStop();
        merlin.unbind();
    }

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        detachView();
        super.onDestroy();
    }


    protected abstract void attachView();

    protected abstract void detachView();

    protected abstract void setupComponent();

    protected abstract Merlin initMerlin();

    protected abstract void addEvents();

    protected void registerConnectable(Connectable connectable) {
        merlin.registerConnectable(connectable);
    }

    protected void registerDisconnectable(Disconnectable disconnectable) {
        merlin.registerDisconnectable(disconnectable);
    }

    protected void registerBindable(Bindable bindable) {
        merlin.registerBindable(bindable);
    }

    protected void loadAvatar(String url, ImageView ivAvatar) {
        if (url == null || ivAvatar == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .dontAnimate()
                .into(ivAvatar);
    }

    protected ActivityComponets getActivityComponent() {
        if (activityComponets == null) {
            activityComponets = DaggerActivityComponets.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponets(ApplicationDagger.getInstance(this).getComponent())
                .build();
    }
        return activityComponets;
    }


    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void showToastDisconnect() {
        Toasty.error(this, getString(R.string.error_missing_network), 200).show();
    }

}
