package com.easywork.jobportal.services;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    //for the custom authentication success handling
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository, PasswordEncoder passwordEncoder){
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    //this function receives the data from the UsersController and then saves them in the MySQL/Hibernate entities using Repository 
    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));

        //encoding the user's password here:
        users.setPassword(passwordEncoder.encode(users.getPassword()));

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

    public Object getCurrentUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users users = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user" ));
            int userId = users.getUserId();


            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
               RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
               return recruiterProfile;
            }else{
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
                return jobSeekerProfile;
            }
        }
        
        return null;

    }


    public Users getCurrentUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users users = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user" ));
                return users;
        }

        return null;

    }



}
