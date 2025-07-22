
package com.easywork.jobportal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.easywork.jobportal.entity.JobPostActivity;
import com.easywork.jobportal.entity.JobSeekerProfile;
import com.easywork.jobportal.entity.JobSeekerSave;
import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.services.JobPostActivityService;
import com.easywork.jobportal.services.JobSeekerProfileService;
import com.easywork.jobportal.services.JobSeekerSaveService;
import com.easywork.jobportal.services.UsersService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class JobSeekerSaveController {

    private final UsersService usersService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobPostActivityService jobPostActivityService;
    private final JobSeekerSaveService jobSeekerSaveService;



    public JobSeekerSaveController(UsersService usersService, JobSeekerProfileService jobSeekerProfileService,
            JobPostActivityService jobPostActivityService, JobSeekerSaveService jobSeekerSaveService) {
            this.usersService = usersService;
            this.jobSeekerProfileService = jobSeekerProfileService;
            this.jobPostActivityService = jobPostActivityService;
            this.jobSeekerSaveService = jobSeekerSaveService;
    }


    //a function to save a selected job
@PostMapping("job-details/save/{id}")
public String save(@PathVariable("id") int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
        String currentUsername = authentication.getName();
        Users user = usersService.findByEmail(currentUsername);
        Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
        JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);

        if (seekerProfile.isPresent() && jobPostActivity != null) {
            JobSeekerSave jobSeekerSave = new JobSeekerSave();
            jobSeekerSave.setJob(jobPostActivity);
            jobSeekerSave.setUserId(seekerProfile.get());

            jobSeekerSaveService.addNew(jobSeekerSave);
        } else {
            throw new RuntimeException("User or job post not found");
        }
    }
    return "redirect:/dashboard/";
}



    @GetMapping("saved-jobs/")
    public String savedJobs(Model model){
        //get a list of jobs for the given user
        List<JobPostActivity> jobPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getCadidatesJob((JobSeekerProfile) currentUserProfile);
        for(JobSeekerSave jobSeekerSave : jobSeekerSaveList){
            jobPost.add(jobSeekerSave.getJob());
        }

        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);

        return "saved-jobs";
    
    }



    //add functionality to delete a job from saved
    //fix the (saved) not showing up.
    




}
