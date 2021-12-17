package com.up.tx.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.up.tx.manager.dto.TransactionDto;
import com.up.tx.manager.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	
	@Query("SELECT new com.up.tx.manager.dto.TransactionDto(t.id, t.timestamp, t.transactionId, t.amount, t.transactionType, t.originUserId, t.targetCuit, t.status) " +
            "FROM Transaction t \n" +
            "WHERE t.originUserId = ?1" )
	List<TransactionDto> getAllTransactionsById(Long id);
	
}
