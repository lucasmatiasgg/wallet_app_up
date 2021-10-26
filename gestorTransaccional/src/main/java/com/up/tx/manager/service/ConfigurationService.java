package com.up.tx.manager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.tx.manager.model.Configuration;
import com.up.tx.manager.repository.ConfigurationRepository;

@Service
public class ConfigurationService {

    private final ConfigurationRepository repository;

    @Autowired
    public ConfigurationService(final ConfigurationRepository repository){
        this.repository = repository;
    }
    
    public String getById(final String id){
        if(repository.findById(id).isPresent()) {
        	return repository.findById(id).get().getValue();
        }
        return null;
    }
    
    public Configuration create(){
        return repository.save(new Configuration());
    }
    
    public Configuration create(Configuration config){
        return repository.save(config);
    }
}