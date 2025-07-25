package com.easywork.jobportal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="skills")
public class Skills {
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String experienceLevel;

    private String yearsOfExperience;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "job_seeker_profile")
    private JobSeekerProfile jobSeekerProfile;


    public Skills(){
        //empty constructor
    }

    public Skills(String experienceLevel, Integer id, JobSeekerProfile jobSeekerProfile, String name, String yearsOfExperience) {
        this.experienceLevel = experienceLevel;
        this.id = id;
        this.jobSeekerProfile = jobSeekerProfile;
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public JobSeekerProfile getJobSeekerProfile() {
        return jobSeekerProfile;
    }

    public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
        this.jobSeekerProfile = jobSeekerProfile;
    }

    @Override
    public String toString() {
        return "Skills [id=" + id + ", name=" + name + ", experienceLevel=" + experienceLevel + ", yearsOfExpereience="
                + yearsOfExperience + ", jobSeekerProfile=" + jobSeekerProfile + "]";
    }


    

}
