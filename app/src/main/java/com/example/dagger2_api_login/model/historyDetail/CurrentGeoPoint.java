
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentGeoPoint implements Parcelable {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    protected CurrentGeoPoint(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Creator<CurrentGeoPoint> CREATOR = new Creator<CurrentGeoPoint>() {
        @Override
        public CurrentGeoPoint createFromParcel(Parcel in) {
            return new CurrentGeoPoint(in);
        }

        @Override
        public CurrentGeoPoint[] newArray(int size) {
            return new CurrentGeoPoint[size];
        }
    };

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(latitude);
            parcel.writeDouble(longitude);
    }
}
