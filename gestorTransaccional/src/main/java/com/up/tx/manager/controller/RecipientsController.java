package com.up.tx.manager.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.up.tx.manager.exceptions.UserAlreadyExistException;
import com.up.tx.manager.login.service.UserService;
import com.up.tx.manager.model.Constants;
import com.up.tx.manager.model.Recipients;
import com.up.tx.manager.model.Response;
import com.up.tx.manager.service.ConfigurationService;
import com.up.tx.manager.service.RecipientsService;

@RestController
@RequestMapping("/v1/recipients")
public class RecipientsController {
	
	@Autowired
	ConfigurationService configurationService;
    
	@Autowired
	UserService userService;
	
	@Autowired
	RecipientsService recipientsService;
	
	@PostMapping("/add")
    public ResponseEntity<Response> createRecipient(@RequestBody Recipients recipient){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(recipient.getName()) && !StringUtils.isEmpty(recipient.getCvu()) 
        			&& !StringUtils.isEmpty(recipient.getAliasCVU())) {

        		Recipients newRecipient = new Recipients(recipient.getName(), recipient.getIdUser(), 
        				recipient.getCvu(), recipient.getAliasCVU());
        		
				recipientsService.create(newRecipient);
        		
        		
        		response.putItem("message", Constants.CREATE_RECIPIENT_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.CREATE_USER_FIELD_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (UserAlreadyExistException e){
            e.printStackTrace();
            Response response = new Response();
            response.putItem("message", Constants.RECIPIENT_ALREADY_EXIST_ERROR_MESSAGE);
            response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_ERROR_CODE);
            
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
	
	
	@GetMapping("/getAll/{idUser}")
    public ResponseEntity<Response> getAllRecipients(@PathVariable(value = "idUser", required = true)Long idUser){
    	Response response = new Response();
    		
    		List<Recipients> recipients = recipientsService.findRecipientsByUserId(idUser);
    		
    		response.putItem("recipients", recipients);
			response.putItem("message", Constants.GENERIC_OK_MESSAGE);
			response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
			return ResponseEntity.ok(response);
    			
	
	}
	
	
	@DeleteMapping("/delete/{cvu}")
    public ResponseEntity<Response> deleteRecipient(@PathVariable(value = "cvu", required = true)String cvu){
        try{
        	Response response = new Response();
        	
        	if(!StringUtils.isEmpty(cvu)) {
        		
        		Long id = recipientsService.getRecipientsId(cvu);
        		recipientsService.deleteRecipient(id);
        		
        		response.putItem("message", Constants.GENERIC_OK_MESSAGE);
        		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
        		
        		return ResponseEntity.ok(response);
        	}

        	response.putItem("message", Constants.DELETE_RECIPIENT_ERROR_MESSAGE);
    		response.putItem(Constants.STATUS_CODE, Constants.DELETE_RECIPIENT_ERROR_CODE);
    		return ResponseEntity.ok(response);
    		
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
        }
    }
}
