package com.example.dagger2_api_login.base;

import androidx.annotation.StringRes;

public interface BaseContract {

    interface BaseView {

        void showProgress(boolean show);

        void showError(int stringResId);

    }

    interface BasePresenter<T> {
        void attachView(T view);

        void detachView();
    }

}
