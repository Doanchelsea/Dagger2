
package com.example.dagger2_api_login.model.historyDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("trip")
    @Expose
    private Trip trip;
    @SerializedName("tripPackageDetail")
    @Expose
    private TripPackageDetail tripPackageDetail;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public TripPackageDetail getTripPackageDetail() {
        return tripPackageDetail;
    }

    public void setTripPackageDetail(TripPackageDetail tripPackageDetail) {
        this.tripPackageDetail = tripPackageDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
