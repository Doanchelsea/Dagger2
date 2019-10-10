package com.example.dagger2_api_login.ui.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseActivity;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.ui.login.contract.LoginContract;
import com.example.dagger2_api_login.ui.login.presenter.LoginPresenter;
import com.example.dagger2_api_login.ui.main.activity.MainActivity;
import com.example.dagger2_api_login.widget.LoadingDialog;
import com.jakewharton.rxbinding3.view.RxView;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements LoginContract.View, Connectable, Disconnectable, Bindable {

    @BindView(R.id.edNumber)
    EditText edNumber;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Inject
    LoginPresenter presenter;


    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        context.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
        presenter.attachView(this);
    }

    @Override
    protected void detachView() {
        presenter.detachView();
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
        addDisposable(RxView.clicks(btnLogin).subscribe(aVoid -> {
            String edNumbe = edNumber.getText().toString().trim();
            showProgress(true);
            presenter.checkExistDriverServer(edNumbe);
        }));
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

    }

    @Override
    public void onDisconnect() {
        showProgress(false);
        showToastDisconnect();
    }



    @Override
    public void onResult(Results results) {
        presenter.saveUserInfoSharedPreferences(results);
        ShowMain();
        showProgress(false);
    }


    private void ShowMain() {
        MainActivity.startActivity(this);
        finish();
    }

}
