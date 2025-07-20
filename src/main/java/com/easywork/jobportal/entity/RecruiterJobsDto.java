package com.easywork.jobportal.entity;






public class RecruiterJobsDto {
    
    private Long totalCandidates;
    private Integer jobPostId;
    private String jobTitle;
    private JobLocation jobLocationId;
    private JobCompany jobCompanyId;

    public RecruiterJobsDto() {
    }

    public RecruiterJobsDto(JobCompany jobCompanyId, JobLocation jobLocationId, Integer jobPostId, String jobTitle, Long totalCandidates) {
        this.jobCompanyId = jobCompanyId;
        this.jobLocationId = jobLocationId;
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.totalCandidates = totalCandidates;
    }

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    @Override
    public String toString() {
        return "RecruiterJobsDto [totalCandidates=" + totalCandidates + ", jobPostId=" + jobPostId + ", jobTitle="
                + jobTitle + ", jobLocationId=" + jobLocationId + ", jobCompanyId=" + jobCompanyId + "]";
    }


    


}
