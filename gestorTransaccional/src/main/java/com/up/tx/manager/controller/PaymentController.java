package com.up.tx.manager.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.up.tx.manager.exceptions.FundsInsufficientsException;
import com.up.tx.manager.login.model.User;
import com.up.tx.manager.login.service.UserService;
import com.up.tx.manager.model.Constants;
import com.up.tx.manager.model.Payment;
import com.up.tx.manager.model.Response;
import com.up.tx.manager.model.Transaction;
import com.up.tx.manager.model.Transfer;
import com.up.tx.manager.service.AccountService;
import com.up.tx.manager.service.ConfigurationService;
import com.up.tx.manager.service.TransactionService;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {
	
	private static final String PAYMENT_TYPE = "Pago";
	private static final String CONFIRMED = "Confirmada";
	private static final String REJECTED = "Rechazada";
	private static final String TRANSFER_CVU_TYPE = "Transferencia por CVU";
	private static final String CASH_IN_TYPE = "Ingreso de dinero";
	
	@Autowired
	TransactionService transactionService;

	@Autowired
	ConfigurationService configurationService;
    
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/processPayment")
    public ResponseEntity<Response> processPayment(@RequestBody final Payment payment){
    	Response response = new Response();
    	Transaction transaction = new Transaction(payment.getAmount(), PAYMENT_TYPE, payment.getOriginUserId(), payment.getCuit(), "");
    	 try{
    		
    		
    		if(!StringUtils.isEmpty(payment.getCuit()) && payment.getOriginUserId() != null) {
			User targetUser = userService.getInfoUserByCuit(payment.getCuit());
    			
    				
			//Luego de todas las aprobaciones actualizo el saldo en la cuenta del comercio
			if(targetUser != null) {
				Double newBalance = accountService.updateAccountBalanceByIdUser(targetUser.getId(), payment.getAmount());
				
				HashMap<String, Object> accountInfo = new HashMap<>();
				
				accountInfo.put("accountBalance", newBalance);
				response.putItem("accountInfo", accountInfo);
				
			}
			
			
			transaction.setStatus(CONFIRMED);
			transactionService.create(transaction);
			response.putItem("transactionId", transaction.getTransactionId());
			response.putItem("message", Constants.GENERIC_OK_MESSAGE);
			response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
			return ResponseEntity.ok(response);
    			
    		}
			
			transaction.setStatus(REJECTED);
			transactionService.create(transaction);
			response.putItem("transactionId", transaction.getTransactionId());
			response.putItem("message", Constants.TRANSACTION_ERROR_MESSAGE);
			response.putItem(Constants.STATUS_CODE, Constants.TRANSACTION_ERROR_CODE);
			return ResponseEntity.ok(response);
			
    			
    	 
    	 }catch (Exception e) {
    		 e.printStackTrace();
    		 transaction.setStatus(REJECTED);
 			 transactionService.create(transaction);
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
    	 }
    	
    }
	
	@PostMapping("/processTransferCVU")
    public ResponseEntity<Response> processTransferCVU(@RequestBody final Transfer transfer){
    	Response response = new Response();
    	boolean byAlias = !StringUtils.isEmpty(transfer.getAliasCVU());
    			
    	Transaction transaction = new Transaction(transfer.getAmount(), TRANSFER_CVU_TYPE, transfer.getOriginUserId(), 
    			byAlias ? transfer.getAliasCVU() : transfer.getCvu(), "");
    	 try{
    		
    		 if(!StringUtils.isEmpty(transfer.getAliasCVU()) || !StringUtils.isEmpty(transfer.getCvu())) {
    			 String cvuOrAlias = byAlias ? transfer.getAliasCVU().toUpperCase() : transfer.getCvu();
    			 
    			 if(transfer.getAmount() != null  && transfer.getOriginUserId() != null) {
    				 
					 Double newBalance = accountService.updateAccountBalanceByAliasOrCVU(cvuOrAlias, transfer.getAmount(), byAlias, transfer.getOriginUserId());
    					 
					 HashMap<String, Object> accountInfo = new HashMap<>();
	    				
    				 accountInfo.put("accountBalance", newBalance);
    				 response.putItem("accountInfo", accountInfo);
    				 
    				 transaction.setStatus(CONFIRMED);
    				 transactionService.create(transaction);
    				 response.putItem("transactionId", transaction.getTransactionId());
    				 response.putItem("message", Constants.GENERIC_OK_MESSAGE);
    				 response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
    				 return ResponseEntity.ok(response);
    			 }
    		 }
			
			transaction.setStatus(REJECTED);
			transactionService.create(transaction);
			response.putItem("transactionId", transaction.getTransactionId());
			response.putItem("message", Constants.TRANSACTION_ERROR_MESSAGE);
			response.putItem(Constants.STATUS_CODE, Constants.TRANSACTION_ERROR_CODE);
			return ResponseEntity.ok(response);
			
    			
    	 }catch(FundsInsufficientsException e) {
				transaction.setStatus(REJECTED);
				transactionService.create(transaction);
				response.putItem("transactionId", transaction.getTransactionId());
				response.putItem("message", Constants.FUNDS_INSUFFICIENTS_ERROR_MESSAGE);
				response.putItem(Constants.STATUS_CODE, Constants.FUNDS_INSUFFICIENTS_ERROR_CODE);
				return ResponseEntity.ok(response);
    	 }catch (Exception e) {
    		 e.printStackTrace();
    		 transaction.setStatus(REJECTED);
 			 transactionService.create(transaction);
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
    	 }
    	
    }
	
	@PostMapping("/processCashIn")
    public ResponseEntity<Response> processCashIn(@RequestBody final Payment payment){
    	Response response = new Response();
    	Transaction transaction = new Transaction(payment.getAmount(), CASH_IN_TYPE, payment.getOriginUserId(), "", "");
    	 try{
    		
    		
    		if(payment.getAmount() != null && payment.getOriginUserId() != null) {
    				
    				Double newBalance = accountService.updateAccountBalance(payment.getOriginUserId(), payment.getAmount());
    			
    				transaction.setStatus(CONFIRMED);
    				transactionService.create(transaction);
    				
    				HashMap<String, Object> accountInfo = new HashMap<>();
    				
    				accountInfo.put("accountBalance", newBalance);
    				response.putItem("accountInfo", accountInfo);
    				response.putItem("transactionId", transaction.getTransactionId());
    				response.putItem("message", Constants.GENERIC_OK_MESSAGE);
    				response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
    				return ResponseEntity.ok(response);
    			}
    			
			transaction.setStatus(REJECTED);
			transactionService.create(transaction);
			response.putItem("transactionId", transaction.getTransactionId());
			response.putItem("message", Constants.TRANSACTION_ERROR_MESSAGE);
			response.putItem(Constants.STATUS_CODE, Constants.TRANSACTION_ERROR_CODE);
			return ResponseEntity.ok(response);
			
    			
    	 
    	 }catch (Exception e) {
    		 e.printStackTrace();
    		 transaction.setStatus(REJECTED);
 			 transactionService.create(transaction);
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE, e);
    	 }
    	
    }
}
