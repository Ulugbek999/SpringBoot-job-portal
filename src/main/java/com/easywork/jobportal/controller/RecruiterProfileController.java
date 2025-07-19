package com.easywork.jobportal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easywork.jobportal.entity.RecruiterProfile;
import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.repository.UsersRepository;
import com.easywork.jobportal.services.RecruiterProfileService;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;

    @Autowired
    public RecruiterProfileController(RecruiterProfileService recruiterProfileService, UsersRepository usersRepository){
        this.usersRepository = usersRepository;
        this.recruiterProfileService = recruiterProfileService;
    }
    

    //a method to show the recruiter profile form:
    @GetMapping("/")
    public String recruiterProfile(Model model){

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if(!(authentication instanceof AnonymousAuthenticationToken)){
        String currentUsername = authentication.getName();
        
        //JPA 
        Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find the user"));

        //user an Optional object to validate the user:
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());


            if(!recruiterProfile.isEmpty()){
                model.addAttribute("profile", recruiterProfile.get());
            }
        }

        return "recruiter_profile";
    }
}
