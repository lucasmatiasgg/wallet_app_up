package com.up.tx.manager.login.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.up.tx.manager.exceptions.EncryptPasswordException;
import com.up.tx.manager.exceptions.UserAlreadyExistException;
import com.up.tx.manager.exceptions.UserNotFoundException;
import com.up.tx.manager.login.dto.UserDto;
import com.up.tx.manager.login.model.User;
import com.up.tx.manager.login.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    public User createUser(String userName, String password, String firstName, String lastName, boolean isCommerce, String socialReason, String address, String cuit, String dni){

        if(userRepository.userExists(userName) > 0){
            throw new UserAlreadyExistException();
        }
        
        User newUser = null;
        try {
        	if(!isCommerce) {
        		newUser = new User(null, userName, passwordEncoder.encode(password), firstName, lastName, isCommerce, dni);
        	} else {
        		newUser = new User(null, userName, passwordEncoder.encode(password), firstName, lastName, isCommerce, socialReason, address, cuit);
        	}
        		
        	
        	userRepository.save(newUser);
        
        } catch (Exception e) {
			e.printStackTrace();
			throw new EncryptPasswordException();
		}
        
        return newUser;
    }
    
    public String getPasswordByUserName(String userName){

    	String pass = null;
    	
        if(userRepository.userExists(userName) > 0){
            pass = userRepository.getPasswordByUserName(userName);
        } else {
        	throw new UserNotFoundException();
        }

        return pass;
    }
    
    public Boolean validateCredentiales(String userName, String password) throws UserNotFoundException{

    	String pass = getPasswordByUserName(userName);
    	
    	try {
			if(passwordEncoder.matches(password, pass)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;

    }
    
    public User getInfoUserByUserName(String userName) {
    	
    	UserDto userDto = userRepository.findUserByUserName(userName);
    	
    	if(userDto != null) {
    		return new User(userDto.getId(), userDto.getUserName(), null, userDto.getFirstName(), userDto.getLastName(), userDto.isCommerce(), userDto.getSocialReason(), userDto.getAddress(), userDto.getCuit());
    	} else {
    		throw new UserNotFoundException();
    	}
    }
    
    public User getInfoUserByCuit(String cuit) {
    	
    	UserDto userDto = userRepository.findUserByCuit(cuit);
    	
    	if(userDto != null) {
    		return new User(userDto.getId(), userDto.getUserName(), null, userDto.getFirstName(), userDto.getLastName(), userDto.isCommerce(), userDto.getSocialReason(), userDto.getAddress(), userDto.getCuit());    		
    	} else {
    		throw new UserNotFoundException();
    	}
    	
    }


    public User getInfoById(Long id) {
    	return userRepository.findById(id).get();
    }
    
    public Long getUserId(String userName) {
    	
    	Long userId = userRepository.getUserId(userName);
    	
    	if(userId != null) {
    		return userId;    		
    	} else {
    		throw new UserNotFoundException();
    	}
    	
    }
}
