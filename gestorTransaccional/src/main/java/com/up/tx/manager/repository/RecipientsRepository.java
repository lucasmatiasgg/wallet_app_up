package com.up.tx.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.up.tx.manager.dto.RecipientsDto;
import com.up.tx.manager.model.Recipients;

@Repository
public interface RecipientsRepository extends JpaRepository<Recipients, Long> {

	@Query("SELECT new com.up.tx.manager.dto.RecipientsDto(r.id, r.idUser, r.name, r.cvu, r.aliasCVU) " +
	            "FROM Recipients r \n" +
	            "WHERE r.idUser = ?1" )
    List<RecipientsDto> findRecipientsByUserId(Long idUser);

	@Query("SELECT r.id " +
            "FROM Recipients r \n" +
            "WHERE r.cvu= ?1" )
	Long getRecipientsId(String cvu);
	
	
}
