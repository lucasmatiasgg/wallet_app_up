package com.up.tx.manager.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.tx.manager.dto.CreditCardDto;
import com.up.tx.manager.exceptions.CreditCardNotFoundException;
import com.up.tx.manager.model.CreditCard;
import com.up.tx.manager.repository.CreditCardRepository;

@Service
public class CreditCardService {

    private final CreditCardRepository repository;

    @Autowired
    public CreditCardService(final CreditCardRepository repository){
        this.repository = repository;
    }

    public List<CreditCard> findCreditCardsByUserId(Long idUser){
        
    	List<CreditCardDto> creditCardsDto = repository.findCreditCardsByUserId(idUser);
    	
    	if(creditCardsDto != null) {
    		List<CreditCard> creditCards = new ArrayList<>();
    		
    		for(CreditCardDto cards : creditCardsDto) {
    			
    			creditCards.add(new CreditCard(cards.getIdUser(), cards.getNumber(), cards.getName(), cards.getExpirationDate(), cards.getBrand(), cards.getVerificationCode()));
    		}
    		return creditCards;
    	} else {
    		throw new CreditCardNotFoundException();
    	}
    	
    }
    
    public CreditCard create(){
        return repository.save(new CreditCard());
    }
    
    public CreditCard create(CreditCard creditCard){
        return repository.save(creditCard);
    }
    
//    public Double updateAccountBalance(Long idUser, Double amount){
//        
//    	AccountDto accountDto = repository.findAccountByUserId(idUser);
//    	Double newBalance = 0.0;
//    	
//    	if(accountDto != null) {
//    		
//    		Double actualBalance = accountDto.getAccountBalance();
//    		newBalance = actualBalance + amount;
//    		
//    		repository.updateBalance(accountDto.getId(), newBalance);
//    	} else {
//    		throw new AccountNotFoundException();
//    	}
//    		
//    	return newBalance;
//    }
    
    public void deleteCreditCard(Long id) {
    	repository.deleteById(id);
    }
    
    public Long getCreditCardId(String number) {
    	
    	return repository.getCreditCardId(number);
    }
}