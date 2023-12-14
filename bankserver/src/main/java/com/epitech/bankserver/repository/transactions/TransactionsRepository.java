package com.epitech.bankserver.repository.transactions;

import com.epitech.bankserver.model.transactions.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionsRepository extends MongoRepository<Transactions, String> {
    List<Transactions> findBySourceAccountNumber(String sourceAccountNumber);

}
