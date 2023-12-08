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

  public Product updateProduct(String id, Product product) {
    product.setId(id);
    return productRepository.save(product);
  }

  public Product addProduct(Product product) {
    return productRepository.insert(product);
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
}
