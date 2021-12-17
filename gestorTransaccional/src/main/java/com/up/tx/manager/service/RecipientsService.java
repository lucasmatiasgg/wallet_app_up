package com.up.tx.manager.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.tx.manager.dto.RecipientsDto;
import com.up.tx.manager.exceptions.RecipientsNotFoundException;
import com.up.tx.manager.model.Recipients;
import com.up.tx.manager.repository.RecipientsRepository;

@Service
public class RecipientsService {

    private final RecipientsRepository repository;

    @Autowired
    public RecipientsService(final RecipientsRepository repository){
        this.repository = repository;
    }

    public List<Recipients> findRecipientsByUserId(Long idUser){
        
    	List<RecipientsDto> recipientsDto = repository.findRecipientsByUserId(idUser);
    	
    	if(recipientsDto != null) {
    		List<Recipients> recipients = new ArrayList<>();
    		
    		for(RecipientsDto recipient : recipientsDto) {
    			recipients.add(new Recipients(recipient.getId(), recipient.getName(), recipient.getIdUser(), recipient.getCvu(), recipient.getAliasCvu()));
    		}
    		
    		return recipients;
    		
    	} else {
    		throw new RecipientsNotFoundException();
    	}
    	
    }
    
    public Recipients create(){
        return repository.save(new Recipients());
    }
    
    public Recipients create(Recipients recipients){
        return repository.save(recipients);
    }
    
    public void deleteRecipient(Long id) {
    	repository.deleteById(id);
    }
    
    public Long getRecipientsId(String cvu) {
    	
    	return repository.getRecipientsId(cvu);
    }
}