package com.example.dagger2_api_login.ui.account.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseFragment;
import com.example.dagger2_api_login.ui.account.contract.AccountContract;
import com.example.dagger2_api_login.ui.account.presenter.AccountPresenter;
import com.example.dagger2_api_login.ui.history.fragment.HistoryFragment;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;
import com.example.dagger2_api_login.ui.splash.activity.SplashActivity;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends BaseFragment implements AccountContract.View {

    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.btnLogout)
    Button btnLogout;

    @Inject
    AccountPresenter presenter;


    @Override
    public void onResume() {
        super.onResume();
        presenter.getName();
        presenter.getAvatar();

    }

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void addEvents() {
        addDisposable(RxView.clicks(btnLogout).subscribe(aVoid -> {
            presenter.Logout();
            logout();
        }));
    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initToolbar() {

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
    protected int getLayoutId() {
        return R.layout.account_fragment;
    }



    @Override
    public void showAvatar(String url) {
        loadAvatar(url,imgAvatar);
    }

    @Override
    public void showName(String name) {
        loadFullName(name,tvName);
    }


    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showError(int stringResId) {

    }

    public void logout(){
        SplashActivity.startActivity(activity);
        activity.finish();
    }
}
