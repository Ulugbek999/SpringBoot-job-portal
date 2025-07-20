package com.easywork.jobportal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.RecruiterProfile;
import com.easywork.jobportal.repository.RecruiterProfileRepository;

@Service
public class RecruiterProfileService {
    

    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository){
        this.recruiterProfileRepository = recruiterProfileRepository;
    }


    //using optional to see if the recruiter exists by id
    public Optional<RecruiterProfile> getOne(Integer id){
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }
}
