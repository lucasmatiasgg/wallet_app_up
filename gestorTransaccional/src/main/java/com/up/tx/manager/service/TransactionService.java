package com.up.tx.manager.service;


import com.up.tx.manager.exceptions.NotFoundException;
import com.up.tx.manager.model.Transaction;
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
}