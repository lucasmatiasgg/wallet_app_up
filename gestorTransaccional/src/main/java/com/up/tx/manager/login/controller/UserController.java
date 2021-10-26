package com.up.tx.manager.login.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.up.tx.manager.connectors.ApiConnector;
import com.up.tx.manager.exceptions.UserAlreadyExistException;
import com.up.tx.manager.exceptions.UserNotFoundException;
import com.up.tx.manager.login.model.User;
import com.up.tx.manager.login.service.UserService;
import com.up.tx.manager.model.Account;
import com.up.tx.manager.model.Constants;
import com.up.tx.manager.model.Response;
import com.up.tx.manager.service.AccountService;
import com.up.tx.manager.service.UserDetailServiceCustom;
import com.up.tx.manager.utils.JwtUtil;


@RestController
//@CrossOrigin(origins = "http://localhost:19000")
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailServiceCustom userDetailService;
    
    @Autowired
    JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    AccountService accountService;
    
    public static final String START_CVU = "00000658";
    
    @PostMapping("/createUser")
    public ResponseEntity<Response> createUser(@RequestBody User user){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getPassword()) 
        			&& !StringUtils.isEmpty(user.getFirstName())) {
        		
        		boolean isCommerce = user.isCommerce();
        		String socialReason = isCommerce && !StringUtils.isEmpty(user.getSocialReason())? user.getSocialReason() : "";
        		String address = isCommerce && !StringUtils.isEmpty(user.getAddress())? user.getAddress() : "";
        		String cuit = isCommerce && !StringUtils.isEmpty(user.getCuit()) ? user.getCuit() : "";
        		
        		User newUser = userService.createUser(user.getUserName().trim(), user.getPassword(), 
        				user.getFirstName(), user.getLastName(), user.isCommerce(),socialReason, address, cuit, user.getDni());
        		
        		
        		// Genero la cuenta asociada al cliente
        		Long userId = userService.getUserId(user.getUserName().trim());
        		
        		// Por ahora armamos el numero a mano
        		int clienteNumberRandom1= (int) (Math.random() * 9999999) + 1;
        		int clienteNumberRandom2= (int) (Math.random() * 9999999) + 1;

        		String finalNumber1 = String.format("%07d", clienteNumberRandom1);
        		String finalNumber2 = String.format("%07d", clienteNumberRandom2);
        		String newCVU = START_CVU + finalNumber1 + finalNumber2;
        		
        		Account account = new Account(userId, newCVU, "", 0.0);
        		
        		accountService.create(account);
        		
        		
        		response.putItem("userName", newUser.getUserName());
        		response.putItem("message", Constants.CREATE_USER_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.CREATE_USER_FIELD_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (UserAlreadyExistException e){
            e.printStackTrace();
            Response response = new Response();
            response.putItem("message", Constants.USER_ALREADY_EXIST_ERROR_MESSAGE);
            response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
            
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
    
    @PostMapping("/validateCredentials")
    public ResponseEntity<Response> validateCredentials(@RequestBody User user){
        
    	Response response = new Response();
    	try {
    		
    		if(userService.validateCredentiales(user.getUserName(), user.getPassword())) {
    			
    			//Genero el token a partir de un user/pass validos
    			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
    			
    			final UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());

    	        String jwtToken = jwtUtil.generateToken(userDetails);
    			
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		response.putItem("message", Constants.RESPONSE_OK_CODE);
        		
        		response.putItem("exchangeToken", jwtToken);
            	return ResponseEntity.ok(response);
        	}
        	
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		response.putItem("message", Constants.USER_OR_PASS_ERROR_MESSAGE);
        	
        	return ResponseEntity.ok(response);
    	} catch(UserNotFoundException e) {
    		e.printStackTrace();
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		response.putItem("message", Constants.USER_OR_PASS_ERROR_MESSAGE);
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
			e.printStackTrace();
			response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
			response.putItem("message", Constants.GENERIC_ERROR_MESSAGE);
			return ResponseEntity.ok(response);
		}
    	
    }
    
    @GetMapping("/getUserInfo/{userName}")
    public ResponseEntity<Response> getUserInfo(@PathVariable(value = "userName", required = true)String userName){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(userName)) {
        		HashMap<String, Object> userInfo = new HashMap<>();
        		HashMap<String, Object> accountInfo = new HashMap<>();
        		
        		User user = userService.getInfoUserByUserName(userName);
        		Account account = accountService.findAccountByUserId(user.getId());
        		
        		userInfo.put("id", user.getId());
        		userInfo.put("firstName", user.getFirstName());
        		userInfo.put("lastName", user.getLastName());
        		userInfo.put("isCommerce", user.isCommerce());
        		userInfo.put("socialReason", user.getSocialReason());
        		userInfo.put("address", user.getAddress());
        		userInfo.put("cuit", user.getCuit());
        		
        		accountInfo.put("accountBalance", account.getAccountBalance());
        		accountInfo.put("cvu", account.getCvu());
        		accountInfo.put("aliasCVU", account.getAliasCVU());
        		
        		response.putItem("userInfo", userInfo);
        		response.putItem("accountInfo", accountInfo);
        		response.putItem("message", Constants.GENERIC_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.NO_DATA_FOUND_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
    
    @GetMapping("/getUserCVU/{userName}")
    public ResponseEntity<Response> getUserCVU(@PathVariable(value = "userName", required = true)String userName){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(userName)) {
        		HashMap<String, Object> userInfo = new HashMap<>();
        		
        		User user = userService.getInfoUserByUserName(userName);
        		Account account = accountService.findAccountByUserId(user.getId());
        		
        		userInfo.put("id", user.getId());
        		userInfo.put("cvu", account.getCvu());
        		userInfo.put("cvu", account.getAliasCVU());
        		
        		response.putItem("userInfo", userInfo);
        		response.putItem("message", Constants.GENERIC_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.NO_DATA_FOUND_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
    
    @PostMapping("/setAlias")
    public ResponseEntity<Response> setAlias(@RequestBody Account account){
        
    	Response response = new Response();
    	try {
    		
    		if(!StringUtils.isEmpty(account.getAliasCVU()) && account.getIdUser() != null) {
    			
    			String newAlias = accountService.updateAliasCVU(account.getIdUser(), account.getAliasCVU().toUpperCase());
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		response.putItem("message", Constants.GENERIC_OK_MESSAGE);
        		
        		HashMap<String, Object> accountInfo = new HashMap<>();
				
				accountInfo.put("aliasCVU", newAlias);
				response.putItem("accountInfo", accountInfo);
        		
        		response.putItem("accountInfo", accountInfo);
        		
            	return ResponseEntity.ok(response);
        	}
        	
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		response.putItem("message", Constants.USER_OR_PASS_ERROR_MESSAGE);
        	
        	return ResponseEntity.ok(response);
    	
    	} catch (Exception e) {
			e.printStackTrace();
			response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
			response.putItem("message", Constants.GENERIC_ERROR_MESSAGE);
			return ResponseEntity.ok(response);
		}
    	
    }
}
