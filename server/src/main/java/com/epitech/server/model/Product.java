package com.epitech.server.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
public class Product {

  @Id
  private ObjectId id;
  private String name;
  private float price;
  private String code;

  public Product() {

  }

  public Product(String name, float price, String code) {
    this.name = name;
    this.price = price;
    this.code = code;
  }

  public Product(ObjectId id, String name, float price, String code) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getId() { return this.id.toString(); }

  public void setId(String id) { this.id = new ObjectId(id); }
}
