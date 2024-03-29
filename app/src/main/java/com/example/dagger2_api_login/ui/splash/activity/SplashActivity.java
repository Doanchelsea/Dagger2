package com.example.dagger2_api_login.ui.splash.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseActivity;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.model.error.Error;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;
import com.example.dagger2_api_login.ui.main.activity.MainActivity;
import com.example.dagger2_api_login.ui.splash.contract.SplashContract;
import com.example.dagger2_api_login.ui.splash.presenter.SplashPresenter;
import com.example.dagger2_api_login.untils.NetworkUtils;
import com.example.dagger2_api_login.widget.LoadingDialog;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity implements SplashContract.View , Connectable, Disconnectable, Bindable {

    @Inject
    SplashPresenter splashPresenter;

    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, SplashActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        context.finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerConnectable(this);
        registerDisconnectable(this);
        registerBindable(this);
    }

    @Override
    protected void attachView() {
        splashPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        splashPresenter.detachView();
    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected Merlin initMerlin() {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(this);
    }

    @Override
    protected void addEvents() {

    }

    @Override
    public void showLogin() {
        addDisposable(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    openLoginScreen();
                }));
    }


    @Override
    public void showMain() {
        addDisposable(Observable.just(0).delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                   openMainScreen();
                }));
    }

    @Override
    public void showErrorLastStatus(Error error, DataManager dataManager) {
        showProgress(false);
        if (NetworkUtils.isOnline()) {
            if (error != null && error.getResults().getError() != null) {
                String mess = error.getResults().getError().getMessage();
                if (mess.equalsIgnoreCase(AppConstants.user_login_other_device)) {
                    Toasty.error(this, getString(R.string.login_other_device), 200).show();
                    LoginActivity.startActivity(this);
                    dataManager.clearAllUserInfo();
                }
            } else {
                showToast(R.string.common_noti_error_message);
            }
        } else {
            showToastDisconnect();
        }

    }

    @Override
    public void showDevieID() {
        splashPresenter.saveDeviceIdSharedPreferences();
    }

    @Override
    public void showProgress(boolean show) {
        if (show){
            LoadingDialog.getInstance().showLoading(this);
        }else {
            LoadingDialog.getInstance().hideLoading();

        }
    }

    @Override
    public void showError(int stringResId) {
        Toast.makeText(context, stringResId, Toast.LENGTH_SHORT).show();
        showProgress(false);
    }




    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {
        splashPresenter.getDevieId();
        showProgress(true);

    }

    @Override
    public void onDisconnect() {
        showProgress(false);
        showToastDisconnect();
    }


    private void openLoginScreen() {
        showProgress(false);
        LoginActivity.startActivity(this);
        finish();
    }
    private void openMainScreen() {
        showProgress(false);
        MainActivity.startActivity(this);
        finish();
    }
}
