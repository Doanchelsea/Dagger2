
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("vehicleTypeId")
    @Expose
    private Long vehicleTypeId;
    @SerializedName("estimatedPrice")
    @Expose
    private Double estimatedPrice;
    @SerializedName("estimatedDistance")
    @Expose
    private Long estimatedDistance;
    @SerializedName("vehicleTypeLuxury")
    @Expose
    private Long vehicleTypeLuxury;
    @SerializedName("estimatedDuration")
    @Expose
    private Long estimatedDuration;
    @SerializedName("carTypeName")
    @Expose
    private String carTypeName;

    protected Result(Parcel in) {
            vehicleTypeId = in.readLong();
            estimatedPrice = in.readDouble();
            estimatedDistance = in.readLong();
            vehicleTypeLuxury = in.readLong();
            estimatedDuration = in.readLong();
            carTypeName = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(Long estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public Long getVehicleTypeLuxury() {
        return vehicleTypeLuxury;
    }

    public void setVehicleTypeLuxury(Long vehicleTypeLuxury) {
        this.vehicleTypeLuxury = vehicleTypeLuxury;
    }

    public Long getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Long estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }


    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(vehicleTypeId);
            parcel.writeDouble(estimatedPrice);
            parcel.writeLong(estimatedDistance);
            parcel.writeLong(vehicleTypeLuxury);
            parcel.writeLong(estimatedDuration);
            parcel.writeString(carTypeName);
    }
}
