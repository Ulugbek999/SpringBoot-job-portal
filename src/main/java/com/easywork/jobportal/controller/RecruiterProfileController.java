package com.easywork.jobportal.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.easywork.jobportal.entity.RecruiterProfile;
import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.repository.UsersRepository;
import com.easywork.jobportal.services.RecruiterProfileService;
import com.easywork.jobportal.util.FileUploadUtil;

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



    //a method to add new recruiter profile
    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            //if the user IS authenticated:
            String currentUsername = authentication.getName();
            Users users = 
                    usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not " +
                    "find the user"));
            //Associate recruiter profile with existing user account
            recruiterProfile.setUserID(users);
            recruiterProfile.setUserAccountId(users.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);
        String fileName = "";
        if(!multipartFile.getOriginalFilename().equals("")){
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            //Set image name in the recruiter profile entity
            recruiterProfile.setProfilePhoto(fileName);
        }
        //Save to the database
        RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);
    
        String uploadDir = "photos/recruiter/" + savedUser.getUserAccountId();



        try{
            //Read profile image from request multipartfile and then ->
            // -> savae image on the server in directory: photos/recruiter
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }catch(Exception ex){
            ex.printStackTrace();
        }   




        return "redirect:/dashboard/";
    }
}
