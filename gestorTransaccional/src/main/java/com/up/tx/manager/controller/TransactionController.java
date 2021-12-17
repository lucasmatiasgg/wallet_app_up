package com.up.tx.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up.tx.manager.dto.TransactionRequest;
import com.up.tx.manager.dto.TransactionResponse;
import com.up.tx.manager.mapper.TransactionMapper;
import com.up.tx.manager.model.Constants;
import com.up.tx.manager.model.CreditCard;
import com.up.tx.manager.model.Response;
import com.up.tx.manager.model.Transaction;
import com.up.tx.manager.service.TransactionService;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(final TransactionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(TransactionMapper.from(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody final TransactionRequest request){
        return ResponseEntity
                .ok(TransactionMapper.from(service.create()));
    }
    
    @GetMapping("/getAllTransactions/{id}")
    public ResponseEntity<Response> getAllMovementsById(@PathVariable("id") final Long id){
    	Response response = new Response();
		
		List<Transaction> transactions = service.getAllTransactions(id);
		
		response.putItem("transactions", transactions);
		response.putItem("message", Constants.GENERIC_OK_MESSAGE);
		response.putItem(Constants.STATUS_CODE, Constants.RESPONSE_OK_CODE);
		return ResponseEntity.ok(response);
    	
    	
    }
    
}
