package com.up.tx.manager.mapper;

import com.up.tx.manager.dto.TransactionResponse;
import com.up.tx.manager.model.Transaction;

public class TransactionMapper {

    public static TransactionResponse from(final Transaction transaction) {
        return new TransactionResponse(transaction.getId(), transaction.getCreatedAt());
    }
}
