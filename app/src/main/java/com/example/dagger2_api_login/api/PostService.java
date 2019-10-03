package com.example.dagger2_api_login.api;


import com.example.dagger2_api_login.model.dagger.Dagger;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostService {

    @POST("user/checkuserexist")
    Observable<Dagger> login(@Body RequestBody body);
}
