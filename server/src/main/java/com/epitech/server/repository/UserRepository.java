package com.epitech.server.repository;

import com.epitech.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  @Query("{username:'?0'}")
  Optional<User> findUserByUsername(String username);
}
