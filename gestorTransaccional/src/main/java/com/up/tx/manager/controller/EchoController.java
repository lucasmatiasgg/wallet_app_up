package com.up.tx.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up.tx.manager.dto.EchoResponse;

@RestController
//@CrossOrigin(origins = "http://localhost:19000")
@RequestMapping("/v1/echo")
public class EchoController {

    @GetMapping
    public ResponseEntity<EchoResponse> echo(@RequestHeader(value = "User-Agent") String userAgent){
        
    	return ResponseEntity
                .accepted()
                .header("Content-Type", "application/json")
                .body(EchoResponse.of(userAgent));
    }
}
