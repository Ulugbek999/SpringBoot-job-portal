package com.easywork.jobportal.services;

import org.springframework.stereotype.Service;
import java.util.List;

import com.easywork.jobportal.entity.UsersType;
import com.easywork.jobportal.repository.UsersTypeRepository;

@Service
public class UsersTypeService {
    
    private final UsersTypeRepository usersTypeRepository;

    //construction injection
    public UsersTypeService(UsersTypeRepository usersTypeRepository){
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll(){
        return usersTypeRepository.findAll();
    }

}
