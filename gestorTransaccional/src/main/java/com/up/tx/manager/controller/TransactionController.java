package com.up.tx.manager.controller;

import com.up.tx.manager.dto.TransactionRequest;
import com.up.tx.manager.dto.TransactionResponse;
import com.up.tx.manager.mapper.TransactionMapper;
import com.up.tx.manager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
