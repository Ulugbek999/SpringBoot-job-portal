package com.easywork.jobportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.JobPostActivity;
import com.easywork.jobportal.repository.JobPostActivityRepository;

@Service
public class JobPostActivityService {



    private final JobPostActivityRepository jobPostActivityRepository;


    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository){
        this.jobPostActivityRepository = jobPostActivityRepository;

    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepository.save(jobPostActivity);
    }
    
}
