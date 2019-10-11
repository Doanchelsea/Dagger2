package com.example.dagger2_api_login.ui.main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseActivity;
import com.example.dagger2_api_login.model.dagger.Results;
import com.example.dagger2_api_login.model.dagger.UserInfo;
import com.example.dagger2_api_login.model.history.ResultsHis;
import com.example.dagger2_api_login.ui.account.fragment.AccountFragment;
import com.example.dagger2_api_login.ui.history.fragment.HistoryFragment;
import com.example.dagger2_api_login.ui.home.fragment.HomeFragment;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;
import com.example.dagger2_api_login.ui.main.contract.MainContract;
import com.example.dagger2_api_login.ui.main.presenter.MainPresenter;
import com.example.dagger2_api_login.ui.splash.activity.SplashActivity;
import com.example.dagger2_api_login.widget.LoadingDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.rxbinding3.view.RxView;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements MainContract.View, Connectable, Disconnectable, Bindable {
    private static final String EXTRA_TRIP_CODE = "EXTRA_TRIP_CODE";
    private static final String EXTRA_TRIP_STATUS = "EXTRA_TRIP_STATUS";
    private static final String EXTRA_TRIP_RESULTS = "EXTRA_TRIP_RESULTS";


    public static void startActivity(Activity context) {
        context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        context.finish();
    }

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;


    private Fragment activeFragment;
    private HomeFragment homeFragment = HomeFragment.newInstance();
    private HistoryFragment historyFragment = HistoryFragment.newInstance();
    private AccountFragment accountFragment = AccountFragment.newInstance();

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
        activeFragment = homeFragment;
        loadAllFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    // MainContract + Base Contract
    @Override
    public void showUserInfo(UserInfo userInfo) {


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

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.menu_navigation_home:
                loadFragment(activeFragment, homeFragment);
                activeFragment = homeFragment;
                return true;
//            case R.id.menu_navigation_wallet:
//                loadFragment(activeFragment, walletFragment);
//                activeFragment = walletFragment;
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.toolBar));
//                return true;
            case R.id.menu_navigation_history:
                loadFragment(activeFragment, historyFragment);
                activeFragment = historyFragment;
                return true;
//            case R.id.menu_navigation_notification:
//                loadFragment(activeFragment, notificationFragment);
//                activeFragment = notificationFragment;
//                fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.toolBar));
//                return true;
            case R.id.menu_navigation_account:
                loadFragment(activeFragment, accountFragment);
                activeFragment = accountFragment;
                return true;
        }
        return false;
    };

    private void loadFragment(Fragment activeFragment, Fragment showFragment) {
        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(showFragment).commit();
    }



    private void loadAllFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, accountFragment, "3").hide(accountFragment).commit();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_container, notificationFragment, "3").hide(notificationFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, historyFragment, "2").hide(historyFragment).commit();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_container, walletFragment, "2").hide(walletFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, homeFragment, "1").commit();
    }

}
