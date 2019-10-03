
package com.example.dagger2_api_login.model.dagger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("facebookId")
    @Expose
    private String facebookId;
    @SerializedName("googlePlusId")
    @Expose
    private String googlePlusId;
    @SerializedName("identityCardNumber")
    @Expose
    private String identityCardNumber;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("stateCode")
    @Expose
    private Long stateCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyId")
    @Expose
    private String companyId;
    @SerializedName("birthDay")
    @Expose
    private Long birthDay;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("lastModifiedDate")
    @Expose
    private Long lastModifiedDate;
    @SerializedName("sex")
    @Expose
    private Long sex;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("assigneeVehicleId")
    @Expose
    private String assigneeVehicleId;
    @SerializedName("agencyStatus")
    @Expose
    private Long agencyStatus;
    @SerializedName("commissionRate")
    @Expose
    private Long commissionRate;
    @SerializedName("totalDeposits")
    @Expose
    private Long totalDeposits;
    @SerializedName("reasonCode")
    @Expose
    private Long reasonCode;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("currentLongitude")
    @Expose
    private Double currentLongitude;
    @SerializedName("currentLatitude")
    @Expose
    private Double currentLatitude;
    @SerializedName("lockType")
    @Expose
    private Long lockType;
    @SerializedName("lockToTime")
    @Expose
    private Long lockToTime;
    @SerializedName("lastLogin")
    @Expose
    private Long lastLogin;
    @SerializedName("notifyOption")
    @Expose
    private Long notifyOption;
    @SerializedName("userType")
    @Expose
    private Long userType;
    @SerializedName("userStatus")
    @Expose
    private Long userStatus;
    @SerializedName("verifyStatus")
    @Expose
    private Long verifyStatus;
    @SerializedName("taxCompanyName")
    @Expose
    private String taxCompanyName;
    @SerializedName("taxCompanyAddress")
    @Expose
    private String taxCompanyAddress;
    @SerializedName("taxCompanyTaxNumber")
    @Expose
    private String taxCompanyTaxNumber;
    @SerializedName("driverOnOff")
    @Expose
    private Long driverOnOff;
    @SerializedName("driverType")
    @Expose
    private Long driverType;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    @SerializedName("reputation")
    @Expose
    private Double reputation;
    @SerializedName("isHasPassword")
    @Expose
    private Boolean isHasPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGooglePlusId() {
        return googlePlusId;
    }

    public void setGooglePlusId(String googlePlusId) {
        this.googlePlusId = googlePlusId;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getStateCode() {
        return stateCode;
    }

    public void setStateCode(Long stateCode) {
        this.stateCode = stateCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Long birthDay) {
        this.birthDay = birthDay;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAssigneeVehicleId() {
        return assigneeVehicleId;
    }

    public void setAssigneeVehicleId(String assigneeVehicleId) {
        this.assigneeVehicleId = assigneeVehicleId;
    }

    public Long getAgencyStatus() {
        return agencyStatus;
    }

    public void setAgencyStatus(Long agencyStatus) {
        this.agencyStatus = agencyStatus;
    }

    public Long getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Long commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Long getTotalDeposits() {
        return totalDeposits;
    }

    public void setTotalDeposits(Long totalDeposits) {
        this.totalDeposits = totalDeposits;
    }

    public Long getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(Long reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Long getLockType() {
        return lockType;
    }

    public void setLockType(Long lockType) {
        this.lockType = lockType;
    }

    public Long getLockToTime() {
        return lockToTime;
    }

    public void setLockToTime(Long lockToTime) {
        this.lockToTime = lockToTime;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getNotifyOption() {
        return notifyOption;
    }

    public void setNotifyOption(Long notifyOption) {
        this.notifyOption = notifyOption;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Long getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Long userStatus) {
        this.userStatus = userStatus;
    }

    public Long getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Long verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getTaxCompanyName() {
        return taxCompanyName;
    }

    public void setTaxCompanyName(String taxCompanyName) {
        this.taxCompanyName = taxCompanyName;
    }

    public String getTaxCompanyAddress() {
        return taxCompanyAddress;
    }

    public void setTaxCompanyAddress(String taxCompanyAddress) {
        this.taxCompanyAddress = taxCompanyAddress;
    }

    public String getTaxCompanyTaxNumber() {
        return taxCompanyTaxNumber;
    }

    public void setTaxCompanyTaxNumber(String taxCompanyTaxNumber) {
        this.taxCompanyTaxNumber = taxCompanyTaxNumber;
    }

    public Long getDriverOnOff() {
        return driverOnOff;
    }

    public void setDriverOnOff(Long driverOnOff) {
        this.driverOnOff = driverOnOff;
    }

    public Long getDriverType() {
        return driverType;
    }

    public void setDriverType(Long driverType) {
        this.driverType = driverType;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Boolean getHasPassword() {
        return isHasPassword;
    }

    public void setHasPassword(Boolean hasPassword) {
        isHasPassword = hasPassword;
    }
}
