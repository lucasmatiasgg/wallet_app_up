package com.up.tx.manager.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.up.tx.manager.exceptions.UserAlreadyExistException;
import com.up.tx.manager.login.model.User;
import com.up.tx.manager.login.service.UserService;
import com.up.tx.manager.model.Account;
import com.up.tx.manager.model.Constants;
import com.up.tx.manager.model.CreditCard;
import com.up.tx.manager.model.Response;
import com.up.tx.manager.service.ConfigurationService;
import com.up.tx.manager.service.CreditCardService;

@RestController
@RequestMapping("/v1/creditCard")
public class CreditCardController {
	
	@Autowired
	ConfigurationService configurationService;
    
	@Autowired
	UserService userService;
	
	@Autowired
	CreditCardService creditCardService;
	
	@PostMapping("/add")
    public ResponseEntity<Response> createCreditCard(@RequestBody CreditCard creditCard){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(creditCard.getName()) && !StringUtils.isEmpty(creditCard.getNumber()) 
        			&& !StringUtils.isEmpty(creditCard.getExpirationDate()) && creditCard.getVerificationCode() != null) {

        		CreditCard newCreditCard = new CreditCard(creditCard.getIdUser(), creditCard.getNumber(), creditCard.getName(), 
        				creditCard.getExpirationDate(), creditCard.getBrand(), creditCard.getVerificationCode());
        		
				creditCardService.create(newCreditCard);
        		
        		
        		response.putItem("message", Constants.CREATE_CREDIT_CARD_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.CREATE_USER_FIELD_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (UserAlreadyExistException e){
            e.printStackTrace();
            Response response = new Response();
            response.putItem("message", Constants.CREDIT_CARD_ALREADY_EXIST_ERROR_MESSAGE);
            response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
            
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
	
	
	@GetMapping("/getAll/{idUser}")
    public ResponseEntity<Response> getCreditCards(@PathVariable(value = "idUser", required = true)Long idUser){
    	Response response = new Response();
    		
    		List<CreditCard> creditCards = creditCardService.findCreditCardsByUserId(idUser);
    		
    		response.putItem("creditCards", creditCards);
			response.putItem("message", Constants.GENERIC_OK_MESSAGE);
			response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
			return ResponseEntity.ok(response);
    			
	
	}
	
	
	@DeleteMapping("/delete/{number}")
    public ResponseEntity<Response> deleteCreditCard(@PathVariable(value = "number", required = true)String number){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(number)) {
        		
        		Long id = creditCardService.getCreditCardId(number);
        		creditCardService.deleteCreditCard(id);
        		
        		response.putItem("message", Constants.GENERIC_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.DELETE_CREDIT_CARD_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.DELETE_CREDIT_CARD_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
}
