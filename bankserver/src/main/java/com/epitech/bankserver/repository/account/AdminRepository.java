package com.epitech.bankserver.repository.account;

import com.epitech.bankserver.model.account.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

}
