package com.easywork.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easywork.jobportal.entity.JobSeekerProfile;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer>{
        
}
