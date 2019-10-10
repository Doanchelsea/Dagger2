
package com.example.dagger2_api_login.model.history;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("tripPackage")
    @Expose
    private List<TripPackage> tripPackage;

    public List<TripPackage> getTripPackage() {
        return tripPackage;
    }

    public void setTripPackage(List<TripPackage> tripPackage) {
        this.tripPackage = tripPackage;
    }

}
