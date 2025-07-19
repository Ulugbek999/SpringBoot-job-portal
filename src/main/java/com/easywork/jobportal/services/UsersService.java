package com.easywork.jobportal.services;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.JobSeekerProfile;
import com.easywork.jobportal.entity.RecruiterProfile;
import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.repository.JobSeekerProfileRepository;
import com.easywork.jobportal.repository.RecruiterProfileRepository;
import com.easywork.jobportal.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final JobSeekerProfileRepository  jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository){
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
    }
    
    //this function receives the data from the UsersController and then saves them in the MySQL/Hibernate entities using Repository 
    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if(userTypeId == 1){
            //then this is a recruiter
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }else{
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }


        return savedUser;
    }


    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
