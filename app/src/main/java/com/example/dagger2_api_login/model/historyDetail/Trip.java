
package com.example.dagger2_api_login.model.historyDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("tripPackageId")
    @Expose
    private String tripPackageId;
    @SerializedName("listPickUpPoint")
    @Expose
    private List<ListPickUpPoint> listPickUpPoint = null;
    @SerializedName("tripStatus")
    @Expose
    private Integer tripStatus;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("startDate")
    @Expose
    private Long startDate;
    @SerializedName("endDate")
    @Expose
    private Long endDate;
    @SerializedName("completedDate")
    @Expose
    private Long completedDate;
    @SerializedName("numberOfSeats")
    @Expose
    private Long numberOfSeats;
    @SerializedName("numberOfVehicles")
    @Expose
    private Long numberOfVehicles;
    @SerializedName("estimatedDistance")
    @Expose
    private Double estimatedDistance;
    @SerializedName("estimatedDuration")
    @Expose
    private Double estimatedDuration;
    @SerializedName("estimatedPrice")
    @Expose
    private Double estimatedPrice;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("tripDescription")
    @Expose
    private String tripDescription;
    @SerializedName("ratingTrip")
    @Expose
    private Long ratingTrip;

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTripPackageId() {
        return tripPackageId;
    }

    public void setTripPackageId(String tripPackageId) {
        this.tripPackageId = tripPackageId;
    }

    public List<ListPickUpPoint> getListPickUpPoint() {
        return listPickUpPoint;
    }

    public void setListPickUpPoint(List<ListPickUpPoint> listPickUpPoint) {
        this.listPickUpPoint = listPickUpPoint;
    }

    public Integer getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(Integer tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Long completedDate) {
        this.completedDate = completedDate;
    }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(Long numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public Double getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(Double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public Long getRatingTrip() {
        return ratingTrip;
    }

    public void setRatingTrip(Long ratingTrip) {
        this.ratingTrip = ratingTrip;
    }
}
