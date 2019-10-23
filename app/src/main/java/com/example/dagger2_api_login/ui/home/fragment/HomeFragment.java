package com.example.dagger2_api_login.ui.home.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseFragment;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.error.Error;
import com.example.dagger2_api_login.ui.home.contract.HomeContract;
import com.example.dagger2_api_login.ui.home.presenter.HomePresenter;
import com.example.dagger2_api_login.ui.login.activity.LoginActivity;
import com.example.dagger2_api_login.ui.main.activity.MainActivity;
import com.example.dagger2_api_login.ui.newtrip.activity.NewTripActivity;
import com.example.dagger2_api_login.widget.LoadingDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.jakewharton.rxbinding3.view.RxView;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.fragment_home_iv_move_search)
    ImageView ivMoveLocationSearch;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void addEvents() {
        addDisposable(RxView.clicks(ivMoveLocationSearch).subscribe(
                unit -> {
                    NewTripActivity.startActivity(activity);

                }
                ));


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
        homePresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        homePresenter.detachView();
    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void showSuccer() {
        showProgress(false);
    }

    @Override
    public void ShowErrorFind(Error error, DataManager manager) {
        showProgress(false);
        if (error.getResults().getMessages() != null){
            Toasty.warning(activity,error.getResults().getMessages()).show();
        }else {
            Toasty.warning(activity,error.getResults().getError().getMessage()).show();
        }
    }

    @Override
    public void showProgress(boolean show) {
        if (show){
            LoadingDialog.getInstance().showLoading(activity);
        }else {
            LoadingDialog.getInstance().hideLoading();
        }
    }
    @Override
    public void showError(int stringResId) {

    }


}
