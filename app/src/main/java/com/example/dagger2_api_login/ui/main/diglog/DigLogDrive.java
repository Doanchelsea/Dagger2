package com.example.dagger2_api_login.ui.main.diglog;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseDialog;
import com.example.dagger2_api_login.model.historyDetail.Results;
import com.example.dagger2_api_login.model.historyDetail.User;
import com.jakewharton.rxbinding3.view.RxView;

import butterknife.BindView;

public class DigLogDrive extends BaseDialog {

    @BindView(R.id.layout_customer_info_iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.layout_customer_info_tv_full_name)
    TextView tv_full_name;
    @BindView(R.id.dialog_trip_detail_btn_close)
    Button btnClose;
    @BindView(R.id.dialog_trip_detail_btn_cancel_trip)
    Button btnCancel;

    private static final String EXTRA_RESULT = "EXTRA_RESULT";

    public static DigLogDrive newInstance(Results results) {
        DigLogDrive dialog = new DigLogDrive();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_RESULT, results);
        dialog.setArguments(args);
        return dialog;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_trip_detail;
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        Results results = bundle.getParcelable(EXTRA_RESULT);
        if (results == null){
            return;
        }

        User user = results.getUser();
        if (user == null){
            return;
        }

        loadFullName(user.getFullName(),tv_full_name);
        loadAvatar(user.getAvatar(),iv_avatar);

    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDialog() {
        Window window = getDialog().getWindow();
        setTransparentDialog(window);
        setFullWidthDialog(window);
    }

    @Override
    protected void addEvents() {
        addDisposable(RxView.clicks(btnClose).subscribe(unit -> {
            hideDialog();
        }));
    }
}
