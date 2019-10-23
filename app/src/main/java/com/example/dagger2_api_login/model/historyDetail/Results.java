
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results implements Parcelable {

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

    @SerializedName("messages")
    @Expose
    private String messages;

    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    protected Results(Parcel in) {
        trip = in.readParcelable(Trip.class.getClassLoader());
        tripPackageDetail = in.readParcelable(TripPackageDetail.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
        vehicle = in.readParcelable(Vehicle.class.getClassLoader());
        messages = in.readString();
        result = in.createTypedArrayList(Result.CREATOR);
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }




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

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(trip, i);
        parcel.writeParcelable(tripPackageDetail, i);
        parcel.writeParcelable(user, i);
        parcel.writeParcelable(vehicle, i);
        parcel.writeString(messages);
        parcel.writeTypedList(result);
    }

}
