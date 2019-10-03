
package com.example.dagger2_api_login.model.dagger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("tokenKey")
    @Expose
    private String tokenKey;
    @SerializedName("refreshKey")
    @Expose
    private String refreshKey;
    @SerializedName("expiryTime")
    @Expose
    private Long expiryTime;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getRefreshKey() {
        return refreshKey;
    }

    public void setRefreshKey(String refreshKey) {
        this.refreshKey = refreshKey;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
