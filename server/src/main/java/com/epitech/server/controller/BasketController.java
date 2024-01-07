package com.epitech.server.controller;

import com.epitech.server.model.Basket;
import com.epitech.server.payment.CreditCard;
import com.epitech.server.service.BasketService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/users")
public class BasketController  {
    @Autowired
    private BasketService basketService;

    public static class AddProductRequest {
        private String productCode;
        private int quantity;

        public AddProductRequest() {}

        public String getProductCode() { return this.productCode; }
        public void setProductCode(String productCode) { this.productCode = productCode; }
        public int getQuantity() { return this.quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    public static class RemoveProductRequest {
        private String productCode;

        public String getProductCode() { return this.productCode; }
        public void setProductCode(String productCode) { this.productCode = productCode; }
    }

    @GetMapping("/{userId}/basket")
    public ResponseEntity<Basket> getBasketByUserId(@PathVariable String userId) {
        Basket basket = basketService.getBasketByUserId(userId);
        if (basket != null) {
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/basket")
    public ResponseEntity<Basket> saveBasket(@PathVariable String userId, @RequestBody Basket basket) {
        Basket savedBasket = basketService.saveBasket(userId, basket);
        if (savedBasket != null) {
            return new ResponseEntity<>(savedBasket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/basket")
    public ResponseEntity<Void> emptyBasket(@PathVariable String userId) {
        basketService.emptyBasket(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{userId}/basket/product")
    public ResponseEntity<Basket> addProductToBasket(
        @PathVariable String userId,
        @RequestBody AddProductRequest request) {
        String productCode = request.getProductCode();
        int quantity = request.getQuantity();
        Basket basket = basketService.addProduct(userId, productCode, quantity);
        if (basket != null) {
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/basket/product")
    public ResponseEntity<Basket> deleteProductFromBasket(
        @PathVariable String userId,
        @RequestBody RemoveProductRequest request) {
        String productCode = request.getProductCode();
        Basket basket = basketService.removeProduct(userId, productCode);
        if (basket != null) {
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/basket/products")
    public ResponseEntity<Basket> deleteAllProductsFromBasket(
        @PathVariable String userId,
        @RequestBody RemoveProductRequest request) {
        String productCode = request.getProductCode();
        Basket basket = basketService.removeAllProducts(userId, productCode);
        if (basket != null) {
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/basket/validate")
    public ResponseEntity<String> validateBasket(
        @PathVariable String userId,
        @RequestBody CreditCard creditCard) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = basketService.validateBasket(userId, creditCard);
        if (response.statusCode() == 200) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response.body(), HttpStatus.BAD_REQUEST);
        }
    }
}
