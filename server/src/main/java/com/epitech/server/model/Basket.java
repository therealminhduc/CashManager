package com.epitech.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("basket")
public class Basket {
  @Id
  private String id;
  @DBRef
  private ArrayList<Product> products;

  public Basket() {
    this.products = new ArrayList<>();
  }

  public Basket(String id) {
    this.id = id;
    this.products = new ArrayList<>();
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  public void addProduct(Product product) {
    this.products.add(product);
  }

  public void removeProduct(Product product) {
    this.products.remove(product);
  }

  public void removeProductWithId(String productId) {
    for (Product product : products) {
      if (product.getId().equals(productId)) {
        products.remove(product);
        return;
      }
    }
  }

  public String getId() { return this.id; }

  public void setId(String id) { this.id = id; }

  public float getValue() {
    float value = 0f;
    for (Product product :
         products) {
      value += product.getPrice();
    }
    return value;
  }
}
