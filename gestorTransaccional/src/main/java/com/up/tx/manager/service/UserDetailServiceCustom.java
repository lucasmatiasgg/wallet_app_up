package com.up.tx.manager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.up.tx.manager.login.dto.UserDto;
import com.up.tx.manager.login.repository.UserRepository;

@Service
public class UserDetailServiceCustom implements UserDetailsService{

	@Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {

        UserDto user = userRepository.findUserByUserName(userName);

        if(user != null){
            return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
        }else {
            return null;
        }

    }
	
}
