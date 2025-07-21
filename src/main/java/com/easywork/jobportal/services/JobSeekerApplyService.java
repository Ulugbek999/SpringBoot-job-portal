package com.easywork.jobportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.JobSeekerApply;
import com.easywork.jobportal.entity.JobSeekerProfile;
import com.easywork.jobportal.repository.JobSeekerApplyRepository;

@Service
public class JobSeekerApplyService {
    

    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    
    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId){
        return jobSeekerApplyRepository.findByUserId(userAccountId);

    }



    public List<JobSeekerApply> getJobCandidates(JobSeekerApply job){
        return jobSeekerApplyRepository.findByJob(job);

    }

}
