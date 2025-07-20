package com.easywork.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easywork.jobportal.entity.JobPostActivity;

public interface JobPostActivityRepository extends JpaRepository<JobPostActivity, Integer> {
    
}
