package com.easywork.jobportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easywork.jobportal.entity.Users;
import com.easywork.jobportal.repository.UsersRepository;
import com.easywork.jobportal.util.CustomUserDetails;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UsersRepository usersRepository;
    
    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find the user. "));
        return new CustomUserDetails(user);
    }
    
}
