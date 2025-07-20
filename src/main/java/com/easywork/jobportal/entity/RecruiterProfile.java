package com.easywork.jobportal.entity;

import java.beans.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recruiter_profile")
public class RecruiterProfile {
    
    @Id
    private int userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userID;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String company;

    @Column(nullable=true, length= 64)
    private String profilePhoto;

    public RecruiterProfile(){
        //empty contstructor
    }

    public RecruiterProfile(String city, String company, String country, String firstName, String lastName, String profilePhoto, String state, int userAccountId, Users userID) {
        this.city = city;
        this.company = company;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
        this.state = state;
        this.userAccountId = userAccountId;
        this.userID = userID;
    }

    public RecruiterProfile(Users users){
        this.userID = users;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Transient // -> because we are not persisting this into the database
    public String getPhotosImagePath(){
        if(profilePhoto == null) return null;
        
        return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecruiterProfile{");
        sb.append("userAccountId=").append(userAccountId);
        sb.append(", userID=").append(userID);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastname=").append(lastName);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", country=").append(country);
        sb.append(", company=").append(company);
        sb.append(", profilePhoto=").append(profilePhoto);
        sb.append('}');
        return sb.toString();
    }


    
    
}
