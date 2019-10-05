package com.example.dagger2_api_login.ui.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseActivity;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;
import com.example.dagger2_api_login.ui.main.contract.MainContract;
import com.example.dagger2_api_login.ui.main.presenter.MainPresenter;
import com.example.dagger2_api_login.ui.splash.activity.SplashActivity;
import com.example.dagger2_api_login.widget.LoadingDialog;
import com.jakewharton.rxbinding3.view.RxView;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements MainContract.View, Connectable, Disconnectable, Bindable {

    @BindView(R.id.btnDangXuat)
    Button btnDangXuat;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;

    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        context.finish();
    }

    @Inject
    MainPresenter mainPresenter;

    // Base Activity
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBindable(this);
        registerConnectable(this);
        registerDisconnectable(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        mainPresenter.detachView();
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

        addDisposable(RxView.clicks(btnDangXuat).subscribe(aVoid -> {
            mainPresenter.SingUp();
            ShowSplash();
        }));
    }



    // MainContract + Base Contract
    @Override
    public void showUserInfo(UserInfo userInfo) {
        loadAvatar(userInfo.getAvatar(),imgAvatar);
        tvName.setText(userInfo.getFullName() + "\n" + userInfo.getAddress());
    }


    private void ShowSplash() {
        LoginActivity.startActivity(this);
        finish();
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

    }




    // Merlin
    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {
        mainPresenter.ShowUserInfoPresenter();
    }

    @Override
    public void onDisconnect() {
        showProgress(false);
        showToastDisconnect();
    }
}
