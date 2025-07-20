package com.easywork.jobportal.entity;


import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;


@Entity
public class JobPostActivity {
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer jobPostId;


    @ManyToOne
    @JoinColumn(name = "postedById", referencedColumnName = "userId")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobLocationId", referencedColumnName="Id")
    private JobLocation jobLocationId;



    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="jobCompanyId", referencedColumnName = "Id")
    private JobCompany jobCompanyId;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isSaved;

    @Length(max=10000)
    private String descriptionOfJob;

    private String jobType;
    private String salary;

    private String remote;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date postedDate;
    private String jobTitle;

    public JobPostActivity(){}

    public JobPostActivity(String descriptionOfJob, Boolean isActive, Boolean isSaved, JobCompany jobCompanyId, JobLocation jobLocationId, Integer jobPostId, String jobTitle, String jobType, Users postedById, Date postedDate, String remote, String salary) {
        this.descriptionOfJob = descriptionOfJob;
        this.isActive = isActive;
        this.isSaved = isSaved;
        this.jobCompanyId = jobCompanyId;
        this.jobLocationId = jobLocationId;
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.postedById = postedById;
        this.postedDate = postedDate;
        this.remote = remote;
        this.salary = salary;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Users getPostedById() {
        return postedById;
    }

    public void setPostedById(Users postedById) {
        this.postedById = postedById;
    }

    public JobLocation getJobLocationId() {
        return jobLocationId;
    }

    public void setJobLocationId(JobLocation jobLocationId) {
        this.jobLocationId = jobLocationId;
    }

    public JobCompany getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(JobCompany jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public String getDescriptionOfJob() {
        return descriptionOfJob;
    }

    public void setDescriptionOfJob(String descriptionOfJob) {
        this.descriptionOfJob = descriptionOfJob;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "JobPostActivity [jobPostId=" + jobPostId + ", postedById=" + postedById + ", jobLocationID="
                + jobLocationId + ", jobCompany=" + jobCompanyId + ", isActive=" + isActive + ", isSaved=" + isSaved
                + ", descriptionOfJob=" + descriptionOfJob + ", jobType=" + jobType + ", salary=" + salary + ", remote="
                + remote + ", postedDate=" + postedDate + ", jobTitle=" + jobTitle + "]";
    }

    
    


}
