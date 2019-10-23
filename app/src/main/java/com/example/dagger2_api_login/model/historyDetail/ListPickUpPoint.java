
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPickUpPoint implements Parcelable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("type")
    @Expose
    private Long type;

    protected ListPickUpPoint(Parcel in) {
        this.address = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.order = in.readString();
        this.type = in.readLong();

    }

    public static final Creator<ListPickUpPoint> CREATOR = new Creator<ListPickUpPoint>() {
        @Override
        public ListPickUpPoint createFromParcel(Parcel in) {
            return new ListPickUpPoint(in);
        }

        @Override
        public ListPickUpPoint[] newArray(int size) {
            return new ListPickUpPoint[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeDouble(longitude);
        parcel.writeDouble(latitude);
        parcel.writeString(order);
        parcel.writeLong(type);

    }
}
