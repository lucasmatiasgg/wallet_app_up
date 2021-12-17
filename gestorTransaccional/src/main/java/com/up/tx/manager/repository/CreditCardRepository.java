package com.up.tx.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.up.tx.manager.dto.CreditCardDto;
import com.up.tx.manager.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	@Query("SELECT new com.up.tx.manager.dto.CreditCardDto(c.id, c.idUser, c.number, c.name, c.expirationDate, c.brand, c.verificationCode) " +
	            "FROM CreditCard c \n" +
	            "WHERE c.idUser = ?1" )
    List<CreditCardDto> findCreditCardsByUserId(Long idUser);
//	
//	@Transactional
//	@Modifying
//	@Query("UPDATE Account " +
//            "SET accountBalance = ?2 \n" +
//            "WHERE id = ?1" )
//	void updateBalance(Long id, Double amount);
//	
	@Query("SELECT c.id " +
            "FROM CreditCard c \n" +
            "WHERE c.number = ?1" )
	Long getCreditCardId(String number);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE CreditCard " +
//            "SET aliasCVU = ?2 \n" +
//            "WHERE id = ?1" )
//	void updateAliasCVU(Long id, String aliasCVU);
	
//	@Query("SELECT new com.up.tx.manager.dto.CreditCardDto(c.id, c.idUser, a.cvu, a.aliasCVU, a.accountBalance) " +
//            "FROM CreditCard c \n" +
//            "WHERE c.idUser = ?1" )
//	CreditCardDto findCreditCardByNumber (String number);
	
}
