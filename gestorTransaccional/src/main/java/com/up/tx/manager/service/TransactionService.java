package com.up.tx.manager.service;


import com.up.tx.manager.dto.CreditCardDto;
import com.up.tx.manager.dto.TransactionDto;
import com.up.tx.manager.exceptions.CreditCardNotFoundException;
import com.up.tx.manager.exceptions.NotFoundException;
import com.up.tx.manager.model.CreditCard;
import com.up.tx.manager.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.tx.manager.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    @Autowired
    public TransactionService(final TransactionRepository repository){
        this.repository = repository;
    }

    public Transaction getById(final Long id){
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundException());
    }
    
    public Transaction create(){
        return repository.save(new Transaction());
    }
    
    public Transaction create(Transaction transaction){
        return repository.save(transaction);
    }
    
    public List<Transaction> getAllTransactions(Long id) {
    	
    	List<TransactionDto> txsDto = repository.getAllTransactionsById(id);
    	
    	
    	if(txsDto != null) {
    		List<Transaction> transactions = new ArrayList<>();
    		
    		for(TransactionDto txDto : txsDto) {
    			
    			transactions.add(new Transaction(txDto.getId(), txDto.getTimestamp(), txDto.getTransactionId(), txDto.getAmount(), txDto.getTransactionType(), 
    	    			txDto.getOriginUserId(), txDto.getTargetCuit(), txDto.getStatus()));
    		}
    		return transactions;
    	} else {
    		throw new CreditCardNotFoundException();
    	}
    	
    }
}