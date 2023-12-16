package com.epitech.server.repository;

import com.epitech.server.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
  @Query("{code:'?0'}")
  Optional<Product> findProductByCode(String code);

}
