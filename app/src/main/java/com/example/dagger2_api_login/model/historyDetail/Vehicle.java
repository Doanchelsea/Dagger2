
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle implements Parcelable {

    @SerializedName("vehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("numberPlate")
    @Expose
    private String numberPlate;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("transportCompanyId")
    @Expose
    private String transportCompanyId;
    @SerializedName("transportCompanyName")
    @Expose
    private String transportCompanyName;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("manufacturerId")
    @Expose
    private String manufacturerId;
    @SerializedName("manufacturerName")
    @Expose
    private String manufacturerName;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("listImages")
    @Expose
    private List<Object> listImages = null;
    @SerializedName("vehicleStatus")
    @Expose
    private Long vehicleStatus;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("productedYear")
    @Expose
    private String productedYear;
    @SerializedName("numberOfSeats")
    @Expose
    private Integer numberOfSeats;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("vehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("vehicleTypeLuxury")
    @Expose
    private Long vehicleTypeLuxury;
    @SerializedName("registerDate")
    @Expose
    private Long registerDate;
    @SerializedName("registerExpiryDate")
    @Expose
    private Long registerExpiryDate;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    @SerializedName("vehicleTypeName")
    @Expose
    private String vehicleTypeName;

    protected Vehicle(Parcel in) {
        this.vehicleId = in.readString();
        this.numberPlate = in.readString();
        this.vehicleName = in.readString();
        this.transportCompanyId = in.readString();
        this.transportCompanyName = in.readString();
        this.origin = in.readString();
        this.manufacturerId = in.readString();
        this.manufacturerName = in.readString();
        this.carType = in.readString();
        this.vehicleStatus = in.readLong();
        this.userId = in.readString();
        this.productedYear = in.readString();
        this.numberOfSeats = in.readInt();
        this.driverId = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.vehicleTypeId = in.readString();
        this.driverName = in.readString();
        this.vehicleTypeLuxury = in.readLong();
        this.registerDate = in.readLong();
        this.registerExpiryDate = in.readLong();
        this.createdDate = in.readLong();
        this.updatedDate = in.readLong();
        this.vehicleTypeName = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(String transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }

    public String getTransportCompanyName() {
        return transportCompanyName;
    }

    public void setTransportCompanyName(String transportCompanyName) {
        this.transportCompanyName = transportCompanyName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public List<Object> getListImages() {
        return listImages;
    }

    public void setListImages(List<Object> listImages) {
        this.listImages = listImages;
    }

    public Long getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Long vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductedYear() {
        return productedYear;
    }

    public void setProductedYear(String productedYear) {
        this.productedYear = productedYear;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
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

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getVehicleTypeLuxury() {
        return vehicleTypeLuxury;
    }

    public void setVehicleTypeLuxury(Long vehicleTypeLuxury) {
        this.vehicleTypeLuxury = vehicleTypeLuxury;
    }

    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }

    public Long getRegisterExpiryDate() {
        return registerExpiryDate;
    }

    public void setRegisterExpiryDate(Long registerExpiryDate) {
        this.registerExpiryDate = registerExpiryDate;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(vehicleId);
        parcel.writeString(numberPlate);
        parcel.writeString(vehicleName);
        parcel.writeString(transportCompanyId);
        parcel.writeString(transportCompanyName);
        parcel.writeString(origin);
        parcel.writeString(manufacturerId);
        parcel.writeString(manufacturerName);
        parcel.writeString(carType);
        parcel.writeLong(vehicleStatus);
        parcel.writeString(userId);
        parcel.writeString(productedYear);
        parcel.writeInt(numberOfSeats);
        parcel.writeString(driverId);
        parcel.writeDouble(longitude);
        parcel.writeDouble(latitude);
        parcel.writeString(vehicleTypeId);
        parcel.writeString(driverName);
        parcel.writeLong(vehicleTypeLuxury);
        parcel.writeLong(registerDate);
        parcel.writeLong(registerExpiryDate);
        parcel.writeLong(createdDate);
        parcel.writeLong(updatedDate);
        parcel.writeString(vehicleTypeName);
    }
}
