package com.example.dagger2_api_login.ui.history.presenter;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.ui.history.contract.DetailContractHistory;
import com.example.dagger2_api_login.untils.StringUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class DetailPresenterHistory extends RxPresenter<DetailContractHistory.View>
        implements DetailContractHistory.Presenter<DetailContractHistory.View>  {

    DataManager dataManager;

    @Inject
     DetailPresenterHistory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getHistoryTripDetailDriver(String tripPackage) {
        if (StringUtils.isEmpty(tripPackage)){
            mView.showError(R.string.common_noti_error);
            return;
        }
        Token token = dataManager.getToken();
        if (token == null){
            mView.showError(R.string.common_noti_error);
            return;
        }
        String tokenKey = dataManager.getToken().getTokenKey();
        if (StringUtils.isEmpty(tokenKey)){
            mView.showError(R.string.common_noti_error);
            return;
        }
        Map<String, String> httpBody = new HashMap<>();
        httpBody.put(AppConstants.KEY_HISTORY_DETAIL_TRIP_ID, tripPackage);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

        Disposable disposable = dataManager.historyDetail(tokenKey,body)
                .flatMap(history -> Observable.just(history.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultsHis -> {
                    mView.showResult(resultsHis);
                }, throwable -> {
                    mView.showError(R.string.common_noti_error);
                });

        addSubscribe(disposable);
    }
}
