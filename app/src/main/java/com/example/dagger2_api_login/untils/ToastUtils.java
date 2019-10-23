package com.example.dagger2_api_login.untils;

import android.content.Context;

import androidx.annotation.StringRes;

import es.dmoral.toasty.Toasty;

public class ToastUtils {
        public static void showToastyNormal(Context context, @StringRes int stringId) {
        Toasty.normal(context, context.getString(stringId), 2000).show();
    }
}
