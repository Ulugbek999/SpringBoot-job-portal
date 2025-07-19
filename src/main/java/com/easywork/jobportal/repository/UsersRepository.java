package com.easywork.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easywork.jobportal.entity.Users;

public interface UsersRepository extends JpaRepository <Users, Integer>{

}