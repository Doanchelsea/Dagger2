package com.example.dagger2_api_login.model.locationbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationBody {

    @SerializedName("startlocation")
    @Expose
    private StartLocation startlocation;

    @SerializedName("dropOffOne")
    @Expose
    private DropOffOne dropOffOne;

    @SerializedName("dropOffTwo")
    @Expose
    private DropOffTwo dropOffTwo;


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

    @SerializedName("vehicleTypeId")
    @Expose
    private Long vehicleTypeId;

    public LocationBody(StartLocation startlocation, DropOffOne dropOffOne, DropOffTwo dropOffTwo, Double estimatedPrice, Long estimatedDistance, Long vehicleTypeLuxury, Long estimatedDuration, Long vehicleTypeId) {
        this.startlocation = startlocation;
        this.dropOffOne = dropOffOne;
        this.dropOffTwo = dropOffTwo;

        this.estimatedPrice = estimatedPrice;
        this.estimatedDistance = estimatedDistance;
        this.vehicleTypeLuxury = vehicleTypeLuxury;
        this.estimatedDuration = estimatedDuration;
        this.vehicleTypeId = vehicleTypeId;
    }

    public StartLocation getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(StartLocation startlocation) {
        this.startlocation = startlocation;
    }

    public DropOffOne getDropOffOne() {
        return dropOffOne;
    }

    public void setDropOffOne(DropOffOne dropOffOne) {
        this.dropOffOne = dropOffOne;
    }

    public DropOffTwo getDropOffTwo() {
        return dropOffTwo;
    }

    public void setDropOffTwo(DropOffTwo dropOffTwo) {
        this.dropOffTwo = dropOffTwo;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
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

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
