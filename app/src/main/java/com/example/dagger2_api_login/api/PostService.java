package com.example.dagger2_api_login.api;


import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.model.dagger.Dagger;
import com.example.dagger2_api_login.model.error.Error;
import com.example.dagger2_api_login.model.history.History;
import com.example.dagger2_api_login.model.historyDetail.HistoryDetail;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PostService {

    @POST("user/checkuserexist")
    Observable<Dagger> login(@Body RequestBody body);

    @POST("user/getHistoryTrip/v2")
    Observable<History> history(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey,
                                @Body RequestBody body);

    // requesrt Api khi dùng SharedPreferences chuyển màn sang MainActivity
    @POST("user/getlaststatus")
    Observable<History> getLastStatusDriver(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey);

    @POST("user/getHistoryTripDetail/v2")
    Observable<HistoryDetail> historyDetail(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey,
                                            @Body RequestBody body);
    @POST("api-driver/ratingtrippackage")
    Observable<HistoryDetail> rattingBarTrip(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey,
                                            @Body RequestBody body);
    @POST("api-trip/findTripByLocation")
    Observable<Error> findTripByLocation(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey,
                                         @Body RequestBody body);
    @POST("user/getlaststatus")
    Observable<HistoryDetail> getLastStatus(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey);

    @POST("config/getLstCarType")
    Observable<HistoryDetail> getLstCarType(@Header(AppConstants.COMMON_ETRANS_TOKEN) String tokenKey,
                                         @Body RequestBody body);

}
