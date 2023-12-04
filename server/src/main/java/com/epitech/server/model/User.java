package com.epitech.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class User {
  @Id
  private String id;
  private String username;
  private String password;
  @DBRef
  private Basket basket;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.basket = new Basket();
  }

  public User(String username, String password, Basket basket) {
    this.username = username;
    this.password = password;
    this.basket = basket;
  }

  public User(String id, String username, String password, Basket basket) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.basket = basket;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getId() {
    return id;
  }
}
