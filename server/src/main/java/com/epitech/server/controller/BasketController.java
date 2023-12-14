package com.epitech.server.controller;

import com.epitech.server.model.Basket;
import com.epitech.server.service.BasketService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class BasketController  {
    @Autowired
    private BasketService basketService;

    public static class AddProductRequest {
        private String productId;
        private int quantity;

        public AddProductRequest() {}

        public String getProductId() { return this.productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public int getQuantity() { return this.quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
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
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        Basket basket = basketService.addProduct(userId, productId, quantity);
        if (basket != null) {
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
