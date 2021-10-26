package com.up.tx.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.up.tx.manager.dto.AccountDto;
import com.up.tx.manager.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT new com.up.tx.manager.dto.AccountDto(a.id, a.idUser, a.cvu, a.aliasCVU, a.accountBalance) " +
	            "FROM Account a \n" +
	            "WHERE a.idUser = ?1" )
    AccountDto findAccountByUserId(Long idUser);
	
	@Transactional
	@Modifying
	@Query("UPDATE Account " +
            "SET accountBalance = ?2 \n" +
            "WHERE id = ?1" )
	void updateBalance(Long id, Double amount);
	
	@Query("SELECT new com.up.tx.manager.dto.AccountDto(a.id, a.idUser, a.cvu, a.aliasCVU, a.accountBalance) " +
            "FROM Account a \n" +
            "WHERE a.cvu = ?1" )
	AccountDto findAccountByCVU(String cvu);
	
	@Query("SELECT new com.up.tx.manager.dto.AccountDto(a.id, a.idUser, a.cvu, a.aliasCVU, a.accountBalance) " +
            "FROM Account a \n" +
            "WHERE a.aliasCVU = ?1" )
	AccountDto findAccountByAliasCVU(String aliasCvu);
	
	@Transactional
	@Modifying
	@Query("UPDATE Account " +
            "SET aliasCVU = ?2 \n" +
            "WHERE id = ?1" )
	void updateAliasCVU(Long id, String aliasCVU);
	
	@Query("SELECT new com.up.tx.manager.dto.AccountDto(a.id, a.idUser, a.cvu, a.aliasCVU, a.accountBalance) " +
            "FROM Account a \n" +
            "WHERE a.idUser = ?1" )
	AccountDto findAccountByIdUser (Long idUser);
	
}
