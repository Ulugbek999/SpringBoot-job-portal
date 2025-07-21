package com.easywork.jobportal.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {



    //A function to redirect users to their respective dashboards after they login
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                
            String username = userDetails.getUsername();
            System.out.println("The username " + username + " is logged in.");
            boolean hasJobSeekerRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Job Seeker"));

            boolean hasRecruiter = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Recruiter"));

            if(hasRecruiter || hasJobSeekerRole){
                response.sendRedirect("/dashboard/");
            }

    }
    







}
