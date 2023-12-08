package com.epitech.server.controller;
import com.epitech.server.model.Product;
import com.epitech.server.model.User;
import com.epitech.server.service.ProductService;
import com.epitech.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.awt.*;

@RestController
@RequestMapping("/api/products")
public class ProductController  {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> users = productService.getAllProducts();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id ) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        /*if (!id.equals(product.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.deleteProductById(id);*/
        productService.deleteProductById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
