package com.epitech.bankserver.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String getCurrentDB() {
        return mongoTemplate.getDb().getName();
    }
}
