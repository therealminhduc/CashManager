package com.epitech.bankserver.repository.user;

import com.epitech.bankserver.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findUserByEmail(String email);

    void deleteUserById(String id);

    Optional<User> findUserById(String id);

}
