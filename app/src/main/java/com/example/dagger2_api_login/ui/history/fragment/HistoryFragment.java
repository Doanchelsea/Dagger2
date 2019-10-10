package com.example.dagger2_api_login.ui.history.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.BaseFragment;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.model.history.TripSection;
import com.example.dagger2_api_login.ui.history.adapter.HistoryAdapter;
import com.example.dagger2_api_login.ui.history.contract.ContractHistory;
import com.example.dagger2_api_login.ui.history.presenter.PresenterHistory;
import com.example.dagger2_api_login.widget.CustomLoadMore;
import com.example.dagger2_api_login.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class HistoryFragment extends BaseFragment implements ContractHistory.View {

    @BindView(R.id.fragment_history_toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_history_recycler_view)
    RecyclerView recyclerView;
    private boolean isLoadMore = false;
    @Inject
    PresenterHistory presenter;

    private List<TripSection> tripSectionList = new ArrayList<>();
    private HistoryAdapter adapter = new HistoryAdapter();

    private int page = 1;
    private int count = 8;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void addEvents() {
        adapter.setOnLoadMoreListener(() -> {
            page++;
            addDisposable(Observable.timer(AppConstants.DURATION_DELAY_BEFORE_WORKING_2, TimeUnit.SECONDS)
                    .compose(bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aVoid -> {
                        isLoadMore = true;
                        presenter.getHistoryTripForDriver(String.valueOf(page), String.valueOf(count));
                    }));
        }, recyclerView);
    }

    @Override
    protected void configViews() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.setLoadMoreView(new CustomLoadMore());
        presenter.getHistoryTripForDriver(String.valueOf(0), String.valueOf(count));
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initToolbar() {
        appCompatActivity.setSupportActionBar(toolbar);
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }

    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_fragment;
    }



    @Override
    public void showHistoryTripForDriver(List<TripSection> list) {
        tripSectionList.addAll(list);
        int sizeTransactionList = list.size();
        if (!isLoadMore) {
            adapter.setNewData(list);
        } else {
            if (sizeTransactionList > 0) {
                adapter.addData(list);
            }
            adapter.loadMoreComplete();
        }
        if (adapter.getData().size() < count) {
            adapter.setEnableLoadMore(false);
        } else {
            adapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            LoadingDialog.getInstance().showLoading(activity);
        } else {
            LoadingDialog.getInstance().hideLoading();
        }
    }

    @Override
    public void showError(int stringResId) {
        showProgress(false);
        if (adapter.isLoadMoreEnable()) {
            adapter.loadMoreFail();
        }
        Toast.makeText(context, stringResId, Toast.LENGTH_SHORT).show();
    }
}
