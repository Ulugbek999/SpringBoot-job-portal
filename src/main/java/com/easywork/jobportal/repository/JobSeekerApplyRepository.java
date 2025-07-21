package com.easywork.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easywork.jobportal.entity.JobSeekerApply;
import com.easywork.jobportal.entity.JobSeekerProfile;



@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer>{
    
    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobSeekerApply job);
}
