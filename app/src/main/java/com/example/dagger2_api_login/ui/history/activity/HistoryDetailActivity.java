package com.example.dagger2_api_login.ui.history.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseActivity;
import com.example.dagger2_api_login.model.history.ResultsHis;
import com.example.dagger2_api_login.model.historyDetail.ListPickUpPoint;
import com.example.dagger2_api_login.model.historyDetail.Results;
import com.example.dagger2_api_login.model.historyDetail.Trip;
import com.example.dagger2_api_login.model.historyDetail.User;
import com.example.dagger2_api_login.ui.history.contract.DetailContractHistory;
import com.example.dagger2_api_login.ui.history.presenter.DetailPresenterHistory;
import com.example.dagger2_api_login.widget.LoadingDialog;
import com.jakewharton.rxbinding3.view.RxView;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

public class HistoryDetailActivity extends BaseActivity implements DetailContractHistory.View, Connectable, Disconnectable, Bindable {


    private static final String EXTRA_TRIP_ID = "EXTRA_TRIP_ID";

    @BindView(R.id.activity_history_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_history_detail_iv_back)
    ImageView ivBack;
    @BindView(R.id.activity_history_detail_tv_create_date)
    TextView tvCreateDate;
    @BindView(R.id.history_detail_iv_avatar_user)
    ImageView ivAvatarDriver;
    @BindView(R.id.history_detail_tv_full_name_user)
    TextView tvFullNameDriver;
    @BindView(R.id.activity_history_detail_tv_estimated_price)
    TextView tvEstimatedPrice;
    @BindView(R.id.activity_history_detail_tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.activity_history_detail_tv_trip_status)
    TextView tvTripStatus;
    @BindView(R.id.activity_history_detail_tv_pickup_point)
    TextView tvPickupPoint;
    @BindView(R.id.activity_history_detail_tv_drof_off_one)
    TextView tvDrofOffOne;
    @BindView(R.id.activity_history_detail_tv_drof_off_two)
    TextView tvDrofOffTwo;
    @BindView(R.id.activity_history_detail_iv_marker_drof_off_one)
    ImageView ivMarkerDrofOffOne;
    @BindView(R.id.activity_history_detail_iv_marker_drof_off_two)
    ImageView ivMarkerDrofOffTwo;
    @BindView(R.id.activity_history_detail_view_divider_one)
    View viewDividerOne;
    @BindView(R.id.activity_history_detail_view_divider_two)
    View viewDividerTwo;
    @BindView(R.id.activity_history_detail_iv_dot_one)
    ImageView ivDotOne;
    @BindView(R.id.activity_history_detail_iv_dot_two)
    ImageView ivDotTwo;

    @BindView(R.id.fakeStatusBar)
    View fakeStatusBar;
    @BindView(R.id.mainView)
    RelativeLayout mainView;
    private String tripId;


    public static void startActivity(Context context, String tripId) {
        context.startActivity(new Intent(context, HistoryDetailActivity.class)
                .putExtra(EXTRA_TRIP_ID, tripId));
    }

    @Inject
    DetailPresenterHistory detailPresenterHistory;

    @Override
    protected void onResume() {
        super.onResume();
        registerConnectable(this);
        registerDisconnectable(this);
        registerBindable(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void attachView() {
        detailPresenterHistory.attachView(this);
    }

    @Override
    protected void detachView() {
        detailPresenterHistory.detachView();
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
        showProgress(true);
        // Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        addDisposable(RxView.clicks(ivBack)
                .subscribe(aVoid -> {
                    onBackPressed();
                    finish();
                }));
        if (getIntent() != null) {
            tripId = getIntent().getStringExtra(EXTRA_TRIP_ID);
        }
        detailPresenterHistory.getHistoryTripDetailDriver(tripId);
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
        Toast.makeText(context, stringResId, Toast.LENGTH_SHORT).show();
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
        showProgress(false);
        showToastDisconnect();
    }

    @Override
    public void showResult(Results results) {
        if (results == null) {
            return;
        }
        User user = results.getUser();
        if (user == null) {
            return;
        }
        loadAvatar(user.getAvatar(), ivAvatarDriver);
        loadFullName(user.getFullName(), tvFullNameDriver);

        Trip trip = results.getTrip();
        if (trip == null) {
            return;
        }
        loadDateTime(trip.getCreatedDate(), tvCreateDate);
        loadEstimatedPrice(trip.getEstimatedPrice(), tvEstimatedPrice);
        loadCreateTime(trip.getCreatedDate(), tvCreateTime);
        loadTripStatus(trip.getTripStatus(), tvTripStatus);

        List<ListPickUpPoint> ListPickUpPoint = trip.getListPickUpPoint();
        int sizeListPickUpPoint = ListPickUpPoint.size();

        if (sizeListPickUpPoint == 1) {
            loadPickUpPointAddress(trip.getListPickUpPoint().get(0).getAddress(), tvPickupPoint);
            visible(mainView);

        } else if (sizeListPickUpPoint == 2) {
            loadPickUpPointAddress(trip.getListPickUpPoint().get(0).getAddress(), tvPickupPoint);
            loadDrofOffOneAddress(trip.getListPickUpPoint().get(1).getAddress(), tvDrofOffOne);
            visible(ivDotOne, ivMarkerDrofOffOne, tvDrofOffOne, viewDividerOne,mainView);

        } else if (sizeListPickUpPoint == 3) {
            loadPickUpPointAddress(trip.getListPickUpPoint().get(0).getAddress(), tvPickupPoint);
            loadDrofOffOneAddress(trip.getListPickUpPoint().get(1).getAddress(), tvDrofOffOne);
            loadDrofOffTwoAddress(trip.getListPickUpPoint().get(2).getAddress(), tvDrofOffTwo);
            visible(ivDotOne, ivMarkerDrofOffOne, tvDrofOffOne, viewDividerOne,
                    ivDotTwo, ivMarkerDrofOffTwo, tvDrofOffTwo, viewDividerTwo,mainView);
        }
        showProgress(false);
    }
}
