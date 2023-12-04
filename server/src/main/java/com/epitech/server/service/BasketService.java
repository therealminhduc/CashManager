package com.epitech.server.service;

import com.epitech.server.model.Basket;
import com.epitech.server.model.User;
import com.epitech.server.repository.BasketRepository;
import com.epitech.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

  @Autowired
  BasketRepository basketRepository;

  @Autowired
  UserRepository userRepository;
  /*
  public Basket getBasketByUserId(String userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
  }*/
}
