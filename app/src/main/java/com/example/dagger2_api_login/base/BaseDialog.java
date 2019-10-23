package com.example.dagger2_api_login.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.untils.FormatUtils;
import com.example.dagger2_api_login.untils.StringUtils;
import com.trello.rxlifecycle3.components.support.RxDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseDialog extends RxDialogFragment {

    public static final int DISTANCE_1000_METTER = 1000;
    public static final int DURATION_60_SECOND = 60;
    public static final int DURATION_3600_SECOND = 3600;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LayoutInflater inflater;
    protected FragmentActivity activity;

    private Unbinder unbinder;

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        this.inflater = inflater;
        activity = getSupportActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupCancelableDialog();
        initDatas();
        configViews();
        addEvents();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    protected abstract void initDatas();

    protected abstract void configViews();

    protected abstract void initDialog();

    protected abstract void addEvents();

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected void setTransparentDialog(Window window) {
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    protected void setFullScreenDialog(Window window) {
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setLayout(width, height);
    }

    protected void setFullWidthDialog(Window window) {
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setLayout(width, height);
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

    protected void loadAvatar(String url, ImageView ivAvatar) {
        if (url == null || ivAvatar == null) {
            return;
        }
        Glide.with(activity)
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

    protected void loadPhoneNumber(String phoneNumber, TextView tvPhoneNumber) {
        if (StringUtils.isEmpty(phoneNumber) || tvPhoneNumber == null) {
            return;
        }
        tvPhoneNumber.setText(phoneNumber);
    }

    protected void loadPickUpPointAddress(String pickUpPointAddress, TextView tvPickupPoint) {
        if (StringUtils.isEmpty(pickUpPointAddress) || tvPickupPoint == null) {
            return;
        }
        tvPickupPoint.setText(pickUpPointAddress);
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



    protected void loadEstimatedDistance(double estimatedDistance, TextView tvDistance) {
        if (tvDistance == null) {
            return;
        }
        if (estimatedDistance < DISTANCE_1000_METTER) {
            String estimatedDistanceFormattedMetter = FormatUtils.convertEstimatedDistance(estimatedDistance);
            tvDistance.setText(getString(R.string.common_label_estimated_distance_metter, estimatedDistanceFormattedMetter));
        } else {
            // convert distance to kilo metter
            double estimatedDistanceFormattedKiloMetter = estimatedDistance / DISTANCE_1000_METTER;
            String estimatedDistanceFormattedDecimal = FormatUtils.convertEstimatedDistance(estimatedDistanceFormattedKiloMetter);
            tvDistance.setText(getString(R.string.common_label_estimated_distance_kilo_metter, estimatedDistanceFormattedDecimal));
        }
    }

    protected void loadEstimatedPrice(double estimatedPrice, TextView tvPrice) {
        if (tvPrice == null) {
            return;
        }
        String estimatedPriceFormatted = FormatUtils.convertEstimatedPrice(estimatedPrice);
        tvPrice.setText(getString(R.string.common_label_estimated_price, estimatedPriceFormatted));
    }

    protected void hideDialog() {
        if (getDialog() != null && getDialog().isShowing()) {
            getDialog().dismiss();
        }
    }

    private FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    private void setupCancelableDialog() {
        setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }
}
