package com.example.dagger2_api_login.ui.history.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.base.RxPresenter;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.data.DataManager;
import com.example.dagger2_api_login.model.dagger.Token;
import com.example.dagger2_api_login.model.history.TripPackage;
import com.example.dagger2_api_login.model.history.TripSection;
import com.example.dagger2_api_login.ui.history.contract.ContractHistory;
import com.example.dagger2_api_login.untils.DateUtils;
import com.example.dagger2_api_login.untils.StringUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class PresenterHistory extends RxPresenter<ContractHistory.View>
        implements ContractHistory.Presenter<ContractHistory.View>  {

    private DataManager dataManager;

    private List<TripSection> tripSectionList = new ArrayList<>();

    @Inject
    PresenterHistory(DataManager dataManager) {
        this.dataManager = dataManager;
    }
    @Override
    public void getHistoryTripForDriver(String page, String count) {

        if (StringUtils.isEmpty(page) || StringUtils.isEmpty(count)) {
            Logger.w("page, count null or empty");
            mView.showError(R.string.common_noti_error);
            return;
        }
        Token token = dataManager.getToken();
        if (token == null) {
            Logger.w("token null");
            mView.showError(R.string.common_noti_error);
            return;
        }

        String tokenKey = dataManager.getToken().getTokenKey();
        if (StringUtils.isEmpty(tokenKey)) {
            Logger.w("token key null or empty");
            mView.showError(R.string.common_noti_error);
            return;
        }

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put("page", page);
        httpBody.put("count", count);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(httpBody)).toString());

        Disposable disposable = dataManager.history(tokenKey, body)
                .flatMap(history -> Observable.just(history.getResults().getTripPackage()))
                .map(trip -> {
                    List<TripSection> tripSectionChildList = new ArrayList<>();

                    for (TripPackage tripPackage : trip ){

                            if (!tripSectionList.contains(new TripSection(true,
                                    DateUtils.convertMiliToDate(tripPackage.getCreatedDate())))){
                                tripSectionList.add(new TripSection(true,
                                        DateUtils.convertMiliToDate(tripPackage.getCreatedDate())));
                                tripSectionChildList.add(new TripSection(true,
                                        DateUtils.convertMiliToDate(tripPackage.getCreatedDate())));
                            }

                            tripSectionList.add(new TripSection(tripPackage));
                            tripSectionChildList.add(new TripSection(tripPackage));
                    }
                    return tripSectionChildList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( (success) -> {
                    if (success == null || success.size() < 1){
                        mView.showError(R.string.common_noti_error);
                        return;
                    }

                    mView.showHistoryTripForDriver(success);
                },
                        (error) ->{
                    mView.showError(R.string.common_noti_error);
                        }
                );

             addSubscribe(disposable);
    }

}
