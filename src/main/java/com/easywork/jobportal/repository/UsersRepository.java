package com.easywork.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easywork.jobportal.entity.Users;


public interface UsersRepository extends JpaRepository <Users, Integer>{




    //finding a user by email
    Optional<Users> findByEmail(String email);

}