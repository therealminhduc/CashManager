package com.epitech.server.service;

import com.epitech.server.http.HttpRequests;

import com.epitech.server.model.Basket;
import com.epitech.server.model.Product;
import com.epitech.server.model.User;
import com.epitech.server.payment.CreditCard;
import com.epitech.server.payment.PaymentRequest;
import com.epitech.server.repository.BasketRepository;
import com.epitech.server.repository.ProductRepository;
import com.epitech.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
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

  public Basket addProduct(String userId, String productCode, int quantity) {
    User user = userRepository.findById(userId).orElse(null);
    Product product = productRepository.findProductByCode(productCode).orElse(null);
    if (user == null || product == null) {
      if (user == null) System.out.println("user is null");
      if (product == null) System.out.println("product is null");
      return null;
    }
    Basket userBasket = user.getBasket();
    for (int i = 0; i < quantity; i++) {
      userBasket.addProduct(product);
    }
    basketRepository.save(userBasket);
    return userBasket;
  }

  public Basket removeProduct(String userId, String productCode) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    Basket userBasket = user.getBasket();
    userBasket.removeProductWithCode(productCode);
    basketRepository.save(userBasket);
    return userBasket;
  }

  public Basket removeAllProducts(String userId, String productCode) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    Basket userBasket = user.getBasket();
    userBasket.removeAllProductsWithCode(productCode);
    basketRepository.save(userBasket);
    return userBasket;
  }

  public HttpResponse<String> validateBasket(String userId, CreditCard creditCard) throws URISyntaxException, IOException, InterruptedException {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    Basket basket = user.getBasket();
    float basketValue = basket.getValue();
    PaymentRequest paymentRequest = new PaymentRequest(creditCard, basketValue);
    HttpRequests httpRequests = new HttpRequests();
    HttpResponse<String> validated = httpRequests.postTransaction(paymentRequest);
    if (validated.statusCode() == 200) {
      basket.setProducts(new ArrayList<>());
      basketRepository.save(basket);
    }
    return validated;
  }
}
