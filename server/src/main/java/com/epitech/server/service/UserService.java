package com.epitech.server.service;

import com.epitech.server.model.Basket;
import com.epitech.server.model.User;
import com.epitech.server.repository.BasketRepository;
import com.epitech.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  BasketRepository basketRepository;

  // Use to insert or update a User in the db
  public User saveUser(String id, User user) {
    user.setId(id);
    return userRepository.save(user);
  }

  public User addUser(User user) {
    Basket basket = basketRepository.insert(new Basket());
    user.setBasket(basket);
    return userRepository.insert(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(String id) {
    return userRepository.findById(id).orElse(null);
  }

  public void deleteUserById(String id) {
    userRepository.deleteById(id);
  }
}
