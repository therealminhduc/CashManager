package com.epitech.server.repository;

import com.epitech.server.model.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket, String> {
}
