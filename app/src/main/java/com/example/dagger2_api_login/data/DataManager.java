package com.example.dagger2_api_login.data;

import com.example.dagger2_api_login.api.PostService;

import com.example.dagger2_api_login.model.dagger.Dagger;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class DataManager implements PostService {

    private PostService postService;


    public DataManager(PostService postService) {
        this.postService = postService;
    }

    @Override
    public Observable<Dagger> login(RequestBody body) {
        return postService.login(body);
    }

}
