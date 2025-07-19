package com.easywork.jobportal.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    @Column(unique=true) // to make sure the email addresses are unique
    private String email;

    @NotEmpty
    private String password;

    private boolean isActive;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date registrationDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userTypeId", referencedColumnName = "userTypeId")
    private UsersType userTypeId;

    public Users(String email, boolean isActive, String password, Date registrationDate, int userId, UsersType userTypeId) {
        this.email = email;
        this.isActive = isActive;
        this.password = password;
        this.registrationDate = registrationDate;
        this.userId = userId;
        this.userTypeId = userTypeId;
    }

    //overload the method so the instance works in the controller
    public Users(){
        
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UsersType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UsersType userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "Users [userId=" + userId + ", email=" + email + ", password=" + password + ", isActive=" + isActive
                + ", registrationDate=" + registrationDate + ", userTypeId=" + userTypeId + ", getUserId()="
                + getUserId() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", isIsActive()="
                + isIsActive() + ", getRegistrationDate()=" + getRegistrationDate() + ", getUserTypeId()="
                + getUserTypeId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    
}
