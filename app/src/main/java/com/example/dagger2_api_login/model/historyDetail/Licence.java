
package com.example.dagger2_api_login.model.historyDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Licence {

    @SerializedName("driverLicenceId")
    @Expose
    private String driverLicenceId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("licenceNo")
    @Expose
    private String licenceNo;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("issueDate")
    @Expose
    private Long issueDate;
    @SerializedName("expiryDate")
    @Expose
    private Long expiryDate;
    @SerializedName("scanFront")
    @Expose
    private String scanFront;
    @SerializedName("scanBack")
    @Expose
    private String scanBack;
    @SerializedName("verifyStatus")
    @Expose
    private Long verifyStatus;

    public String getDriverLicenceId() {
        return driverLicenceId;
    }

    public void setDriverLicenceId(String driverLicenceId) {
        this.driverLicenceId = driverLicenceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Long issueDate) {
        this.issueDate = issueDate;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getScanFront() {
        return scanFront;
    }

    public void setScanFront(String scanFront) {
        this.scanFront = scanFront;
    }

    public String getScanBack() {
        return scanBack;
    }

    public void setScanBack(String scanBack) {
        this.scanBack = scanBack;
    }

    public Long getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Long verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
