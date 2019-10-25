package com.example.dagger2_api_login.ui.newtrip.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseActivity;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.data.eventbus.CanEvent;
import com.example.dagger2_api_login.data.eventbus.NewEvent;
import com.example.dagger2_api_login.linsenner.LisennerNewTrip;
import com.example.dagger2_api_login.model.error.Error;
import com.example.dagger2_api_login.model.historyDetail.Result;
import com.example.dagger2_api_login.model.historyDetail.Results;
import com.example.dagger2_api_login.model.locationbody.DropOffOne;
import com.example.dagger2_api_login.model.locationbody.DropOffTwo;
import com.example.dagger2_api_login.model.locationbody.LocationBody;
import com.example.dagger2_api_login.model.locationbody.StartLocation;
import com.example.dagger2_api_login.ui.home.presenter.HomePresenter;
import com.example.dagger2_api_login.ui.main.diglog.DigLogDrive;
import com.example.dagger2_api_login.ui.newtrip.adapter.TypeAdapter;
import com.example.dagger2_api_login.ui.newtrip.contract.NewTripContract;
import com.example.dagger2_api_login.ui.newtrip.presenter.NewTripPresenter;
import com.example.dagger2_api_login.untils.DateUtils;
import com.example.dagger2_api_login.untils.FormatUtils;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class NewTripActivity extends BaseActivity implements NewTripContract.View, Connectable, Disconnectable, Bindable, OnMapReadyCallback, LisennerNewTrip {

    private Location currentLocation;
    private FusedLocationProviderClient locationClient;
    private GoogleMap driverMap;
    private LocationCallback locationCallback;
    private int currentLocationCount = 0;
    private List<Result> resultsList = new ArrayList<>();
    private TypeAdapter typeAdapter;
    private LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayout.HORIZONTAL,false);

    @Inject
    NewTripPresenter newTripPresenter;

    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tv_quang_duong)
    TextView tv_quang_duong;
    @BindView(R.id.tv_Price)
    TextView tv_Price;
    @BindView(R.id.constraintLayout_type)
    ConstraintLayout constraintLayout;
    @BindView(R.id.recyclerView_type)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_home_iv_move_location)
    ImageView ivMoveLocation;
    @BindView(R.id.fragment_home_iv_move_search_bar)
    ImageView ivMoveLocationSearch;
    @BindView(R.id.searchBar)
    MaterialSearchBar materialSearchBar;
    double estimatedPrice;
    long estimatedDistance,vehicleTypeLuxury,estimatedDuration,vehicleTypeId;
    List<LocationBody> locationBodyList = new ArrayList<>();
    StartLocation startLocation;
    DropOffOne dropOffOne;
    DropOffTwo dropOffTwo;
    DigLogDrive dialog;



    private List<AutocompletePrediction> predictionList;
    private PlacesClient mplacesClient;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,NewTripActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBindable(this);
        registerConnectable(this);
        registerDisconnectable(this);
        EventBus.getDefault().register(this);

        if (currentLocation != null) {
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            driverMap.moveCamera(cameraUpdate);
        }
        NewTripActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);

    }

    @Override
    protected void onPause() {
        if (locationClient != null && locationCallback != null) {
            locationClient.removeLocationUpdates(locationCallback);
        }
        EventBus.getDefault().unregister(this);
        super.onPause();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NewEvent newEvent){
//        String tripPackgeID = newEvent.getTripPackgeId();
//        Toasty.success(this,tripPackgeID).show();
        newTripPresenter.getLastEventBus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CanEvent canEvent){
        dialog.dismiss();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_trip;
    }

    @Override
    protected void attachView() {
        newTripPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        newTripPresenter.detachView();
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
        initMap();
        addDisposable(RxView.clicks(ivMoveLocation).subscribe(unit -> {
            LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            driverMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
        }));

        Places.initialize(this,"AIzaSyA9rTnxqBiap6yioOqcGREcyrcZno2ShGU");
        mplacesClient = Places.createClient(this);
        InputMethodManager inm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        newTripPresenter.SeachBar(materialSearchBar,predictionList,mplacesClient,inm);

        addDisposable(RxView.clicks(ivMoveLocationSearch).subscribe(unit -> {

            showProgress(true);
            if (locationBodyList.size() > 1){
                startLocation = new StartLocation(locationBodyList.get(0).getStartlocation().getLatitude(),
                        locationBodyList.get(0).getStartlocation().getLongitude());

                dropOffOne = new DropOffOne(locationBodyList.get(1).getDropOffOne().getLatitude(),
                        locationBodyList.get(1).getDropOffOne().getLongitude());
            }
            if (locationBodyList.size() > 2){
                dropOffOne = new DropOffOne(locationBodyList.get(2).getDropOffTwo().getLatitude(),
                        locationBodyList.get(2).getDropOffTwo().getLongitude());
            }

            LocationBody locationBody = new LocationBody(startLocation,dropOffOne,dropOffTwo,
                    estimatedPrice,estimatedDistance,vehicleTypeLuxury,estimatedDuration,vehicleTypeId);
            String jsonString = JSON.toJSONString(locationBody);
            newTripPresenter.jsonString(jsonString);
            Log.d("dâdaad",jsonString);


        }));


    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()){
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisconnect() {
        showToastDisconnect();
    }


    private void initMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.driver_map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }
    private void loadMap() {
        if (driverMap != null) {
            // Map is ready
            NewTripActivityPermissionsDispatcher.getMyLocationWithPermissionCheck(this);
            NewTripActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        driverMap = googleMap;
        loadMap();
    }
    // Request để chạy Permissions
    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        NewTripActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    // khi người dùng từ chối cấp quyền permission LOCATION cho hệ thống
    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void showDeniedForLocation() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    // bắt điều khiển khi người dùng từ chối kích hoạt permission
    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void showNeverAskForLocation() {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
//        intent.setData(uri);
//        startActivityForResult(intent, 101);
    }

    // NeedsPermission : khi khởi tạo và bắt đầu sự kiện khi ckeck permission song
    @SuppressWarnings({"MissingPermission"})
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getMyLocation() {
        locationClient = LocationServices.getFusedLocationProviderClient(context);
        locationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                        driverMap.moveCamera(cameraUpdate);
                        onLocationChanged(location);
                    }
                })
                .addOnFailureListener(e -> {
                    Logger.d("Error trying to get last GPS location: %s", e);
                    e.printStackTrace();
                });
    }

    @SuppressWarnings({"MissingPermission"})
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(context);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        buildLocationCallback();

        LocationServices.getFusedLocationProviderClient(context)
                .requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }


    private void buildLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                onLocationChanged(locationResult.getLastLocation());
            }
        };
    }

    @SuppressLint("MissingPermission")
    private void onLocationChanged(Location location) {
        // GPS may be turned off
        if (location == null) {
            return;
        }
        currentLocationCount++;
        // Report to the UI that the location was updated
        currentLocation = location;
        driverMap.setMyLocationEnabled(true);
        driverMap.getUiSettings().setMyLocationButtonEnabled(false);

        if (currentLocationCount == 1) {
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            driverMap.moveCamera(cameraUpdate);
        }

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
        showProgress(false);
    }

    @Override
    public void ShowLatLng(double lat, double lng,String name) {
        driverMap.clear();
        LatLng latLng = new LatLng(lat, lng);
        driverMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .title(name)
                .position(latLng));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        driverMap.moveCamera(cameraUpdate);
        newTripPresenter.showResult(lat,lng,currentLocation.getLatitude(), currentLocation.getLongitude());

        startLocation = new StartLocation(currentLocation.getLatitude(),currentLocation.getLongitude());
        dropOffOne = new DropOffOne(lat,lng);
        dropOffTwo = new DropOffTwo(0.0,0.0);
        showProgress(true);
    }

    @Override
    public void error(int error) {
        showProgress(false);
    }

    @Override
    public void show(List<Result> results) {
        if (results == null){
            return;
        }
        resultsList =  results;
        typeAdapter = new TypeAdapter(this,results,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(typeAdapter);
        loadEstimatedDuration(results.get(0).getEstimatedDuration(),tvTime);
        loadEstimatedPrice(results.get(0).getEstimatedPrice(),tv_Price);
        loadEstimatedDistance(results.get(0).getEstimatedDistance(),tv_quang_duong);

        vehicleTypeId = results.get(0).getVehicleTypeId();
        estimatedPrice = results.get(0).getEstimatedPrice();
        estimatedDistance = results.get(0).getEstimatedDistance();
        vehicleTypeLuxury = results.get(0).getVehicleTypeLuxury();
        estimatedDuration = results.get(0).getEstimatedDuration();

        visible(constraintLayout);
        showProgress(false);
    }

    @Override
    public void ShowSucess() {
        showProgress(false);
        Toasty.success(this,"Thành công").show();
    }

    @Override
    public void ShowErrorFind(Error error, DataManager manager) {
        showProgress(false);
        if (error.getResults().getMessages() != null){
            Toasty.warning(this,error.getResults().getMessages()).show();
        }else {
            Toasty.warning(this,error.getResults().getError().getMessage()).show();
        }
    }

    @Override
    public void showTripPackge(Results results) {
        dialog = DigLogDrive.newInstance(results);
        dialog.show(getSupportFragmentManager(), dialog.getTag());
    }

    @Override
    public void onClickItemType(Result results) {
        loadEstimatedDuration(results.getEstimatedDuration(),tvTime);
        loadEstimatedPrice(results.getEstimatedPrice(),tv_Price);
        loadEstimatedDistance(results.getEstimatedDistance(),tv_quang_duong);

        vehicleTypeId = results.getVehicleTypeId();
        estimatedPrice = results.getEstimatedPrice();
        estimatedDistance = results.getEstimatedDistance();
        vehicleTypeLuxury = results.getVehicleTypeLuxury();
        estimatedDuration = results.getEstimatedDuration();
    }

}
