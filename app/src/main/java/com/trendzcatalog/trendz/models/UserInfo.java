package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class UserInfo {
    private int UserInfoID;
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Email;
    private Date DateCreated;
    private Date LastModified;
    private int UpdatedByID;
    private boolean Active;

    public UserInfo() {
        this.UserInfoID = 0;
    }
    public UserInfo(int UserInfoID, String Username, String Password) {
        this.UserInfoID = UserInfoID;
        this.Username = Username;
        this.Password = Password;
    }
    public UserInfo(int UserInfoID, String Username) {
        this.UserInfoID = UserInfoID;
        this.Username = Username;
    }

    public int getUserInfoID() { return this.UserInfoID; }
    public void setUserInfoID(int UserInfoID ) { this.UserInfoID = UserInfoID; }
    public String getUsername() { return this.Username; }
    public void setUsername(String Username) { this.Username = Username; }
    public String getPassword() { return this.Password; }
    public void setPassword(String Password) { this.Password = Password; }
    public String getFirstName() { return this.FirstName; }
    public void setFirstName(String FirstName) { this.FirstName = FirstName; }
    public String getLastName() { return this.LastName; }
    public void setLastName(String LastName) { this.LastName = LastName; }
    public String getPhoneNumber() { return this.PhoneNumber; }
    public void setPhoneNumber(String PhoneNumber) { this.PhoneNumber = PhoneNumber; }
    public String getEmail() { return this.Email; }
    public void setEmail(String Email) { this.Email = Email; }
    public Date getDateCreated() { return this.DateCreated; }
    public void setDateCreated(Date DateCreated) { this.DateCreated = DateCreated; }
    public Date getLastModified() { return this.LastModified; }
    public void setLastModified(Date LastModified) { this.LastModified = LastModified; }
    public int getUpdatedByID() { return this.UpdatedByID; }
    public void setUpdatedByID(int UpdatedByID) { this.UpdatedByID = UpdatedByID; }
    public boolean getActive() { return this.Active; }
    public void setActive(boolean Active) { this.Active = Active; }
}
