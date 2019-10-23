
package com.example.dagger2_api_login.model.historyDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private String salt;
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
    private Integer sex;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("deviceType")
    @Expose
    private Long deviceType;
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
    @SerializedName("currentGeoPoint")
    @Expose
    private CurrentGeoPoint currentGeoPoint;
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
    @SerializedName("otpCode")
    @Expose
    private String otpCode;
    @SerializedName("verifyLockTime")
    @Expose
    private Long verifyLockTime;
    @SerializedName("verifyStatus")
    @Expose
    private Long verifyStatus;
    @SerializedName("licence")
    @Expose
    private Licence licence;
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
    @SerializedName("updateUser")
    @Expose
    private String updateUser;
    @SerializedName("updateUserFullName")
    @Expose
    private String updateUserFullName;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    @SerializedName("reputation")
    @Expose
    private Double reputation;
    @SerializedName("isHasPassword")
    @Expose
    private Boolean isHasPassword;
    @SerializedName("account")
    @Expose
    private Account account;

    protected User(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.fullName = in.readString();
        this.password = in.readString();
        this.salt = in.readString();
        this.email = in.readString();
        this.facebookId = in.readString();
        this.googlePlusId = in.readString();
        this.identityCardNumber = in.readString();
        this.phoneNumber = in.readString();
        this.stateCode = in.readLong();
        this.description = in.readString();
        this.companyName = in.readString();
        this.companyId = in.readString();
        this.birthDay = in.readLong();
        this.createdDate = in.readLong();
        this.lastModifiedDate = in.readLong();
        this.sex = in.readInt();
        this.avatar = in.readString();
        this.deviceId = in.readString();
        this.deviceType = in.readLong();
        this.language = in.readString();
        this.assigneeVehicleId = in.readString();
        this.agencyStatus = in.readLong();
        this.commissionRate = in.readLong();
        this.totalDeposits = in.readLong();
        this.reasonCode = in.readLong();
        this.district = in.readString();
        this.address = in.readString();
        this.currentGeoPoint = in.readParcelable(CurrentGeoPoint.class.getClassLoader());
        this.currentLongitude = in.readDouble();
        this.currentLatitude = in.readDouble();
        this.lockType = in.readLong();
        this.lockToTime = in.readLong();
        this.lastLogin = in.readLong();
        this.notifyOption = in.readLong();
        this.userType = in.readLong();
        this.userStatus = in.readLong();
        this.otpCode = in.readString();
        this.verifyLockTime = in.readLong();
        this.verifyStatus = in.readLong();
        this.licence = in.readParcelable(Licence.class.getClassLoader());
        this.taxCompanyName = in.readString();
        this.taxCompanyAddress = in.readString();
        this.taxCompanyTaxNumber = in.readString();
        this.driverOnOff = in.readLong();
        this.driverType = in.readLong();
        this.updateUser = in.readString();
        this.updateUserFullName = in.readString();
        this.updatedDate = in.readLong();
        this.reputation = in.readDouble();
        this.account = in.readParcelable(Account.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
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

    public CurrentGeoPoint getCurrentGeoPoint() {
        return currentGeoPoint;
    }

    public void setCurrentGeoPoint(CurrentGeoPoint currentGeoPoint) {
        this.currentGeoPoint = currentGeoPoint;
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

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Long getVerifyLockTime() {
        return verifyLockTime;
    }

    public void setVerifyLockTime(Long verifyLockTime) {
        this.verifyLockTime = verifyLockTime;
    }

    public Long getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Long verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Licence getLicence() {
        return licence;
    }

    public void setLicence(Licence licence) {
        this.licence = licence;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserFullName() {
        return updateUserFullName;
    }

    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(userName);
        parcel.writeString(fullName);
        parcel.writeString(password);
        parcel.writeString(salt);
        parcel.writeString(email);
        parcel.writeString(facebookId);
        parcel.writeString(googlePlusId);
        parcel.writeString(identityCardNumber);
        parcel.writeString(phoneNumber);
        parcel.writeLong(stateCode);
        parcel.writeString(description);
        parcel.writeString(companyName);
        parcel.writeString(companyId);
        parcel.writeLong(birthDay);
        parcel.writeLong(createdDate);
        parcel.writeLong(lastModifiedDate);
        parcel.writeInt(sex);
        parcel.writeString(avatar);
        parcel.writeString(deviceId);
        parcel.writeLong(deviceType);
        parcel.writeString(language);
        parcel.writeString(assigneeVehicleId);
        parcel.writeLong(agencyStatus);
        parcel.writeLong(commissionRate);
        parcel.writeLong(totalDeposits);
        parcel.writeLong(reasonCode);
        parcel.writeString(district);
        parcel.writeString(address);
        parcel.writeParcelable(currentGeoPoint, i);
        parcel.writeDouble(currentLongitude);
        parcel.writeDouble(currentLatitude);
        parcel.writeLong(lockType);
        parcel.writeLong(lockToTime);
        parcel.writeLong(lastLogin);
        parcel.writeLong(notifyOption);
        parcel.writeLong(userType);
        parcel.writeLong(userStatus);
        parcel.writeString(otpCode);
        parcel.writeLong(verifyLockTime);
        parcel.writeLong(verifyStatus);
        parcel.writeParcelable(licence, i);
        parcel.writeString(taxCompanyName);
        parcel.writeString(taxCompanyAddress);
        parcel.writeString(taxCompanyTaxNumber);
        parcel.writeLong(driverOnOff);
        parcel.writeLong(driverType);
        parcel.writeString(updateUser);
        parcel.writeString(updateUserFullName);
        parcel.writeLong(updatedDate);
        parcel.writeDouble(reputation);
        parcel.writeParcelable(account, i);
    }
}
