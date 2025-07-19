package com.easywork.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.entity.UsersType;
import com.easywork.jobportal.services.UsersService;
import com.easywork.jobportal.services.UsersTypeService;

import jakarta.validation.Valid;


@Controller //simple MVC controller
public class UsersController {
    
    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    //constructor injecting:

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService){
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    //A method to show user registration form
    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    //Catching the data from the register.html and then adding new user in the UsersService
    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users){
       // System.out.println("User: : " + users);
        
       usersService.addNew(users);
       return "dashboard";

    }
    
}
