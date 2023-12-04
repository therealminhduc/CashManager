package com.epitech.server.service;

import com.epitech.server.model.Product;
import com.epitech.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public Product getProductById(String id) {
    return productRepository.findById(id).orElse(null);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public void deleteProductById(String id) {
    productRepository.deleteById(id);
  }

  public void deleteProduct(Product product) {
    productRepository.delete(product);
  }
}
