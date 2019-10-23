
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryDetail implements Parcelable {

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("results")
    @Expose
    private Results results;

    protected HistoryDetail(Parcel in) {
        this.code = in.readLong();
        this.count = in.readLong();
        this.status = in.readString();
        this.type = in.readString();
        this.results = in.readParcelable(Results.class.getClassLoader());
    }

    public static final Creator<HistoryDetail> CREATOR = new Creator<HistoryDetail>() {
        @Override
        public HistoryDetail createFromParcel(Parcel in) {
            return new HistoryDetail(in);
        }

        @Override
        public HistoryDetail[] newArray(int size) {
            return new HistoryDetail[size];
        }
    };

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(code);
        parcel.writeLong(count);
        parcel.writeString(status);
        parcel.writeString(type);
        parcel.writeParcelable(results, i);
    }
}
