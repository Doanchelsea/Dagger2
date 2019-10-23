
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Account implements Parcelable {

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

    protected Account(Parcel in) {
        this.userId = in.readString();
        this.totalDcoin = in.readLong();
        this.reputation = in.readDouble();
        this.totalEvaluate = in.readDouble();
        this.totalUserRating = in.readLong();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeLong(totalDcoin);
        parcel.writeDouble(reputation);
        parcel.writeDouble(totalEvaluate);
        parcel.writeLong(totalUserRating);
    }
}
