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

  public Product updateProduct(String code, Product product) {
    Product dbProduct = productRepository.findProductByCode(code).orElse(null);
    if (dbProduct != null) {
      product.setId(dbProduct.getId());
      product.setCode(code);
      return productRepository.save(product);
    }
    return null;
  }

  public Product addProduct(Product product) {
    return productRepository.insert(product);
  }

  public Product getProductByCode(String code) {
    return productRepository.findProductByCode(code).orElse(null);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public void deleteProductByCode(String code) {
    productRepository.findProductByCode(code).ifPresent(productToDelete -> productRepository.delete(productToDelete));
  }
}
