package com.easywork.jobportal.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="job_seeker_profile")
public class JobSeekerProfile {
    

        @Id
        private int userAccountId;

        @OneToOne
        @JoinColumn(name = "user_account_id")
        @MapsId
        private Users userId;

        private String firstName;
        private String lastName;
        private String city;
        private String state;
        private String country;
        private String workAuthorization;
        private String employmentType;
        private String resume;
        @Column(nullable=true, length=64)
        private String profilePhoto;

        @OneToOne(targetEntity = Skills.class, cascade=CascadeType.ALL, mappedBy="jobSeekerProfile")
        private List<Skills> skills;

    public JobSeekerProfile(){
        //empty constructor
    }


    public JobSeekerProfile(String city, String country, String employmentType, String firstName, String lastName, String profilePhoto, String resume, List<Skills> skills, String state, int userAccountId, Users userId, String workAuthorization) {
        this.city = city;
        this.country = country;
        this.employmentType = employmentType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
        this.resume = resume;
        this.skills = skills;
        this.state = state;
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.workAuthorization = workAuthorization;
    }

    public JobSeekerProfile(Users users){
        this.userId = users;
    }


    public int getUserAccountId() {
        return userAccountId;
    }


    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }


    public Users getUserId() {
        return userId;
    }


    public void setUserId(Users userId) {
        this.userId = userId;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
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


    public String getWorkAuthorization() {
        return workAuthorization;
    }


    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }


    public String getEmployeementType() {
        return employmentType;
    }


    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }


    public String getResume() {
        return resume;
    }


    public void setResume(String resume) {
        this.resume = resume;
    }


    public String getProfilePhoto() {
        return profilePhoto;
    }


    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }


    public List<Skills> getSkills() {
        return skills;
    }


    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }


    @Override
    public String toString() {
        return "JobSeekerProfile [userAccountId=" + userAccountId + ", userId=" + userId + ", firstName=" + firstName
                + ", lastName=" + lastName + ", city=" + city + ", state=" + state + ", country=" + country
                + ", workAuthorization=" + workAuthorization + ", employeementType=" + employmentType + ", resume="
                + resume + ", profilePhoto=" + profilePhoto + ", skills=" + skills + "]";
    }

    
    
}
