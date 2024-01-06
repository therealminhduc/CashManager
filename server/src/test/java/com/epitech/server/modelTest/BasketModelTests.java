package com.epitech.server.modelTest;
import com.epitech.server.model.Basket;
import com.epitech.server.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasketModelTests {
    @Test
    public void testBasketCreation() {
        Basket basket = new Basket();

        assertNotNull(basket);
        assertNotNull(basket.getProducts());
        assertEquals(0, basket.getProducts().size());
    }

    @Test
    public void testAddProduct() {
        Basket basket = new Basket();
        Product product = new Product("Product1", 10, "123");

        basket.addProduct(product);

        assertEquals(1, basket.getProducts().size());
        assertEquals(product, basket.getProducts().get(0));
    }

    @Test
    public void testRemoveProduct() {
        Basket basket = new Basket();
        Product product = new Product("Product1", 10, "123");

        basket.addProduct(product);
        basket.removeProduct(product);

        assertEquals(0, basket.getProducts().size());
    }

    @Test
    public void testRemoveProductWithCode() {
        Basket basket = new Basket();
        Product product = new Product("Product1", 10, "123");

        basket.addProduct(product);
        basket.removeProductWithCode("123");

        assertEquals(0, basket.getProducts().size());
    }

    @Test
    public void testGetValue() {
        Basket basket = new Basket();
        Product product1 = new Product("Product1", 10, "123");
        Product product2 = new Product("Product2", 15, "456");

        basket.addProduct(product1);
        basket.addProduct(product2);

        float expectedValue = 10 + 15;
        assertEquals(expectedValue, basket.getValue(), 0.001);
    }
}
