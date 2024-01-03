package com.epitech.bankserver.repository.transactions;

import com.epitech.bankserver.model.transactions.Transactions;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionsRepository
  extends MongoRepository<Transactions, String> {
  List<Transactions> findBySourceAccountNumber(String sourceAccountNumber);
}
