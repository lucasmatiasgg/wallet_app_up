package com.up.tx.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.up.tx.manager.login.repository.UserRepository;
import com.up.tx.manager.model.AuthenticationRequest;
import com.up.tx.manager.model.AuthenticationResponse;
import com.up.tx.manager.model.Constants;
import com.up.tx.manager.service.UserDetailServiceCustom;
import com.up.tx.manager.utils.JwtUtil;

@RestController
public class SecurityController {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailServiceCustom userDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value= "/getToken", method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        }catch(BadCredentialsException e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.USER_OR_PASS_ERROR_MESSAGE, e);
        }catch (InternalAuthenticationServiceException ae){
            ae.printStackTrace();
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, Constants.USER_OR_PASS_ERROR_MESSAGE, ae);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse((jwt)));
    }
    
//    public static String generateJwtToken(String user, String pass) {
//    	
//    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
//    	final UserDetails userDetails = userDetailService.loadUserByUsername(user);
//
//        return jwtUtil.generateToken(userDetails);
//    }
}
