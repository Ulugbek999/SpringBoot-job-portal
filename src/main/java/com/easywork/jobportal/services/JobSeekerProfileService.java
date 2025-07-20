package com.easywork.jobportal.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.JobSeekerProfile;
import com.easywork.jobportal.repository.JobSeekerProfileRepository;



@Service
public class JobSeekerProfileService {

    //a method to get a job seeker profile based on the Id

    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }


    public Optional<JobSeekerProfile> getOne(Integer id){
        
        return jobSeekerProfileRepository.findById(id);
    }
    
}
