
package com.example.dagger2_api_login.model.historyDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("totalDcoin")
    @Expose
    private Long totalDcoin;
    @SerializedName("reputation")
    @Expose
    private Double reputation;
    @SerializedName("totalEvaluate")
    @Expose
    private Double totalEvaluate;
    @SerializedName("totalUserRating")
    @Expose
    private Long totalUserRating;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTotalDcoin() {
        return totalDcoin;
    }

    public void setTotalDcoin(Long totalDcoin) {
        this.totalDcoin = totalDcoin;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Double getTotalEvaluate() {
        return totalEvaluate;
    }

    public void setTotalEvaluate(Double totalEvaluate) {
        this.totalEvaluate = totalEvaluate;
    }

    public Long getTotalUserRating() {
        return totalUserRating;
    }

    public void setTotalUserRating(Long totalUserRating) {
        this.totalUserRating = totalUserRating;
    }
}
