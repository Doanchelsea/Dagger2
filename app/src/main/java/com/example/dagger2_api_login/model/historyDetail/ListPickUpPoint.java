
package com.example.dagger2_api_login.model.historyDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPickUpPoint {

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

}
