package com.easywork.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easywork.jobportal.entity.UsersType;

public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
    
}
