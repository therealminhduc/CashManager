package com.epitech.server.service;

import com.epitech.server.model.Basket;
import com.epitech.server.model.Product;
import com.epitech.server.model.User;
import com.epitech.server.repository.BasketRepository;
import com.epitech.server.repository.ProductRepository;
import com.epitech.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BasketService {

  @Autowired
  BasketRepository basketRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProductRepository productRepository;

  public Basket getBasketByUserId(String userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    return user.getBasket();
  }

  public Basket saveBasket(String userId, Basket basket) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    String basketId = user.getBasket().getId();
    basket.setId(basketId);
    return basketRepository.save(basket);
  }

  public void emptyBasket(String userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return;
    }
    Basket userBasket = user.getBasket();
    userBasket.setProducts(new ArrayList<>());
    basketRepository.save(userBasket);
  }

  public Basket addProduct(String userId, String productId, int quantity) {
    User user = userRepository.findById(userId).orElse(null);
    Product product = productRepository.findById(productId).orElse(null);
    if (user == null || product == null) {
      return null;
    }
    Basket userBasket = user.getBasket();
    for (int i = 0; i < quantity; i++) {
      userBasket.addProduct(product);
    }
    basketRepository.save(userBasket);
    return userBasket;
  }

  public Basket removeProduct(String userId, String productId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    Basket userBasket = user.getBasket();
    userBasket.removeProductWithId(productId);
    basketRepository.save(userBasket);
    return userBasket;
  }

  public boolean validateBasket(String userId, String name, long cardNumber) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return false;
    }
    Basket basket = user.getBasket();
    float basketValue = basket.getValue();
    // VALIDATION LOGIC
    return true;
  }
}
