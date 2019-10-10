package com.example.dagger2_api_login.ui.home.fragment;

import android.os.Bundle;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseFragment;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment {


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void addEvents() {

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
    }

    @Override
    protected void detachView() {

    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }
}
