package com.easywork.jobportal.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.easywork.jobportal.entity.JobPostActivity;
import com.easywork.jobportal.entity.RecruiterJobsDto;
import com.easywork.jobportal.entity.RecruiterProfile;
import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.services.JobPostActivityService;
import com.easywork.jobportal.services.UsersService;



@Controller
public class JobPostActivityController {
    

    private final UsersService usersService;
    private final JobPostActivityService jobPostActivityService;

    @Autowired
    public JobPostActivityController(UsersService usersService, JobPostActivityService jobPostActivityService){
        this.usersService = usersService;
        this.jobPostActivityService = jobPostActivityService;
    }


    //a method to search for jobs
    @GetMapping("/dashboard/")
    public String searchJobs(Model model){

        Object currentUserProfile = usersService.getCurrentUserProfile();



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);


            //to list the jobs 
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                List<RecruiterJobsDto> recruiterJobs = jobPostActivityService.getRecruiterJobs(((RecruiterProfile)currentUserProfile).getUserAccountId());
                model.addAttribute("jobPost", recruiterJobs);
            }



        }
        model.addAttribute("user", currentUserProfile);
        


        return "dashboard";
    }


    //a method to show the form
    @GetMapping("/dashboard/add")
    public String addJobs(Model model){
        model.addAttribute("jobPostActivity", new JobPostActivity());
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-jobs";
    }


    


    //a request method for the add-job dashboard to work
    @PostMapping("/dashboard/addNew")
    public String addNew(JobPostActivity jobPostActivity, Model model){
        

    Users user = usersService.getCurrentUser();
    if(user!= null){
        jobPostActivity.setPostedById(user);
    }
        jobPostActivity.setPostedDate(new Date());
        model.addAttribute("jobPostActivity", jobPostActivity);
        JobPostActivity saved = jobPostActivityService.addNew(jobPostActivity);
        return "redirect:/dashboard/";
    }

}
