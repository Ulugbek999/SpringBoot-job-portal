package com.easywork.jobportal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.IRecruiterJobs;
import com.easywork.jobportal.entity.JobCompany;
import com.easywork.jobportal.entity.JobLocation;
import com.easywork.jobportal.entity.JobPostActivity;
import com.easywork.jobportal.entity.RecruiterJobsDto;
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


    //new method to get the list of recruiter job based on their id
    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){

       List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);
       List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

       //convert the data from the database into dtos:

       for(IRecruiterJobs rec : recruiterJobsDtos){
        JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
        JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");

        //    public RecruiterJobsDto(JobCompany jobCompanyId, JobLocation jobLocationId, Integer jobPostId, String jobTitle, Long totalCandidates) {



        recruiterJobsDtoList.add(new RecruiterJobsDto(comp, loc, rec.getJob_post_id(), rec.getJob_title(), rec.getTotalCandidates()));
       }
       return recruiterJobsDtoList;
    }
    
}
