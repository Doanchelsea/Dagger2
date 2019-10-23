package com.example.dagger2_api_login.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dagger2_api_login.ApplicationDagger;
import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.dagger.components.ActivityComponets;
import com.example.dagger2_api_login.dagger.components.DaggerActivityComponets;
import com.example.dagger2_api_login.dagger.moduls.ActivityModule;
import com.example.dagger2_api_login.untils.DateUtils;
import com.example.dagger2_api_login.untils.FormatUtils;
import com.example.dagger2_api_login.untils.StringUtils;
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

    protected void loadFullName(String fullName, TextView tvFullName) {
        if (StringUtils.isEmpty(fullName) || tvFullName == null) {
            return;
        }
        tvFullName.setText(fullName);
    }

    protected void loadPickUpPointAddress(String pickUpPointAddress, TextView tvPickUpPointAddress) {
        if (StringUtils.isEmpty(pickUpPointAddress) || tvPickUpPointAddress == null) {
            return;
        }
        tvPickUpPointAddress.setText(pickUpPointAddress);
    }

    protected void loadDrofOffOneAddress(String drofOffOneAddress, TextView tvDrofOffOne) {
        if (StringUtils.isEmpty(drofOffOneAddress) || tvDrofOffOne == null) {
            return;
        }
        tvDrofOffOne.setText(drofOffOneAddress);
    }

    protected void loadDrofOffTwoAddress(String drofOffTwoAddress, TextView tvDrofOffTwo) {
        if (StringUtils.isEmpty(drofOffTwoAddress) || tvDrofOffTwo == null) {
            return;
        }
        tvDrofOffTwo.setText(drofOffTwoAddress);
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


    protected void loadDateTime(long createDated, TextView tvCreateDate) {
        if (tvCreateDate == null) {
            return;
        }
        tvCreateDate.setText(DateUtils.convertMiliToDateTime(createDated));
    }

    protected void loadCreateTime(long createTimed, TextView tvCreateTime) {
        if (tvCreateTime == null) {
            return;
        }
        tvCreateTime.setText(getString(R.string.history_label_create_time, DateUtils.convertMiliToTime(createTimed)));
    }

    protected void loadTripStatus(int tripStatus, TextView tvTripStatus) {
        if (tvTripStatus == null) {
            return;
        }
        if (tripStatus == AppConstants.TRIP_STATUS_69) {
            tvTripStatus.setBackgroundResource(R.color.history_color_bg_trip_status_ok);
            tvTripStatus.setText(R.string.history_label_trip_status_success);
        } else {
            tvTripStatus.setBackgroundResource(R.color.history_color_bg_trip_status_cancel);
            tvTripStatus.setText(R.string.history_label_trip_status_cancel);
        }
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

    protected void loadEstimatedDuration(double estimatedDuration, TextView tvDuration) {
        if (tvDuration == null) {
            return;
        }
        if (estimatedDuration <60){
            String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedDuration);
            tvDuration.setText(getString(R.string.common_label_estimated_duration_second, estimatedPriceFormatted));
        }else if (estimatedDuration < 3600){
            double estimatedDurationminute = estimatedDuration/60;
            String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedDurationminute);
            tvDuration.setText(getString(R.string.common_label_estimated_duration_minute, estimatedPriceFormatted));
        }else {
            double estimatedDurationHout = estimatedDuration/3600;
            double estimatedDurationminute = (estimatedDuration - Integer.valueOf((int) estimatedDurationHout)*3600)/60;

            if (estimatedDurationminute <= 0){
                String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedDurationHout);
                tvDuration.setText(getString(R.string.common_label_estimated_duration_hour,estimatedPriceFormatted));
            }else {
                String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedDurationHout);
                String estimatedPriceFormattedHoust = FormatUtils.convertEstimatedPrice(estimatedDurationminute);
                tvDuration.setText(getString(R.string.common_label_estimated_duration_hour,estimatedPriceFormatted)
                        +" "+getString(R.string.common_label_estimated_duration_minute,estimatedPriceFormattedHoust));
            }
        }
    }

    protected void loadEstimatedPrice(double estimatedPrice, TextView tvPrice) {
        if (tvPrice == null) {
            return;
        }
        String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedPrice);
        tvPrice.setText(getString(R.string.common_label_estimated_price, estimatedPriceFormatted));
    }

    protected void loadEstimatedDistance(double estimatedDistance, TextView tvDistance) {
        if (tvDistance == null) {
            return;
        }
        if (estimatedDistance < 1000) {
            String estimatedDistanceFormattedMetter = FormatUtils.convertEstimatedDistance(estimatedDistance);
            tvDistance.setText(getString(R.string.common_label_estimated_distance_metter, estimatedDistanceFormattedMetter));
        } else {
            // convert distance to kilo metter
            double estimatedDistanceFormattedKiloMetter = estimatedDistance / 1000;
            String estimatedDistanceFormattedDecimal = FormatUtils.convertEstimatedDistance(estimatedDistanceFormattedKiloMetter);
            tvDistance.setText(getString(R.string.common_label_estimated_distance_kilo_metter, estimatedDistanceFormattedDecimal));
        }
    }



    public void showToastDisconnect() {
        Toasty.error(this, getString(R.string.error_missing_network), 200).show();
    }
    public void showToast(int mess) {
        Toasty.error(this, getString(mess), 200).show();
    }

}
