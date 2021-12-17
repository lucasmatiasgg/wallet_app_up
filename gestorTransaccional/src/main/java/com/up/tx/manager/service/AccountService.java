package com.up.tx.manager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.tx.manager.dto.AccountDto;
import com.up.tx.manager.exceptions.AccountNotFoundException;
import com.up.tx.manager.exceptions.AccountWithBalanceException;
import com.up.tx.manager.exceptions.FundsInsufficientsException;
import com.up.tx.manager.login.controller.UserController;
import com.up.tx.manager.model.Account;
import com.up.tx.manager.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(final AccountRepository repository){
        this.repository = repository;
    }

    public Account findAccountByUserId(Long idUser){
        
    	AccountDto accountDto = repository.findAccountByUserId(idUser);
    	
    	if(accountDto != null) {
    		return new Account(accountDto.getIdUser(), accountDto.getCvu(), accountDto.getAliasCVU(), accountDto.getAccountBalance());
    	} else {
    		throw new AccountNotFoundException();
    	}
    	
    }
    
    public Account create(){
        return repository.save(new Account());
    }
    
    public Account create(Account account){
        return repository.save(account);
    }
    
    public Double updateAccountBalance(Long idUser, Double amount){
        
    	AccountDto accountDto = repository.findAccountByUserId(idUser);
    	Double newBalance = 0.0;
    	
    	if(accountDto != null) {
    		
    		Double actualBalance = accountDto.getAccountBalance();
    		newBalance = actualBalance + amount;
    		
    		repository.updateBalance(accountDto.getId(), newBalance);
    	} else {
    		throw new AccountNotFoundException();
    	}
    		
    	return newBalance;
    }
    
    public Double updateAccountBalanceByAliasOrCVU(String cvuOrAlias, Double amount, boolean byAlias, Long originUserId){
        
    	AccountDto originAccount = repository.findAccountByUserId(originUserId);
    	Double newOriginBalance = 0.0;
    	
    	//Se valida si el CVU es de FuelPay
    	
    	String entity = byAlias ? "" :cvuOrAlias.substring(0, 8);
    	if(byAlias || UserController.START_CVU.equals(entity)) {
    		AccountDto targetAccount = byAlias ? repository.findAccountByAliasCVU(cvuOrAlias) : repository.findAccountByCVU(cvuOrAlias);
    		Double newTargetBalance = 0.0;
    		
    		if(originAccount != null && targetAccount != null) {
    			
    			Double actualTargetBalance = targetAccount.getAccountBalance();
    			Double actualOriginBalance = originAccount.getAccountBalance();
    			if(actualOriginBalance >= amount) {
    				newTargetBalance = actualTargetBalance + amount;
    				newOriginBalance = actualOriginBalance - amount;
    				
    				repository.updateBalance(targetAccount.getId(), newTargetBalance);
    				repository.updateBalance(originAccount.getId(), newOriginBalance);
    				
    			}else {
    				throw new FundsInsufficientsException();
    			}
    			
    		} else {
    			throw new AccountNotFoundException();
    		}
    		
    		
    		
    	} else {
    		//Si no es un CVU de FuelPay hacemos el descuento en la cuenta origen nada mas y se transiciona
    		
    			if(originAccount != null ) {
    			
    			Double actualOriginBalance = originAccount.getAccountBalance();
    			if(actualOriginBalance >= amount) {
    				newOriginBalance = actualOriginBalance - amount;
    				repository.updateBalance(originAccount.getId(), newOriginBalance);
    				
    			}else {
    				throw new FundsInsufficientsException();
    			}
    			
    		} else {
    			throw new AccountNotFoundException();
    		}
    		
    	}
    	
    	return newOriginBalance;
    }
    
    public String updateAliasCVU(Long idUser, String aliasCVU){
        
    	AccountDto accountDto = repository.findAccountByUserId(idUser);
    	
    	if(accountDto != null) {
    		
    		repository.updateAliasCVU(accountDto.getId(), aliasCVU);
    	} else {
    		throw new AccountNotFoundException();
    	}
    		
    	return aliasCVU;
    }
    
    public Double updateAccountBalanceByIdUser(Long idUser, Double amount){
    	
    	AccountDto targetAccount = repository.findAccountByIdUser(idUser);
    	Double newTargetBalance = 0.0;
    	
    	if(targetAccount != null) {
    		
    		Double actualTargetBalance = targetAccount.getAccountBalance();
			newTargetBalance = actualTargetBalance + amount;
			repository.updateBalance(targetAccount.getId(), newTargetBalance);
    			
    	} else {
    		throw new AccountNotFoundException();
    	}
    		
    	return newTargetBalance;
    }
    
    public void deleteAccount(Long idUser) {
    	
    	AccountDto accountDto = repository.findAccountByUserId(idUser);
    	
    	if(accountDto != null) {
    		if (accountDto.getAccountBalance() == 0 ) {
    			repository.deleteById(accountDto.getId());
    		} else {
    			throw new AccountWithBalanceException();
    		}
    	}
//    	repository.deleteById(idUser);
    }
}