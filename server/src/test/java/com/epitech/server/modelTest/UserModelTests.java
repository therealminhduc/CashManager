package com.epitech.server.modelTest;

import com.epitech.server.model.Basket;
import com.epitech.server.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
public class UserModelTests {

    @Test
    public void  testGettersAndSetters () {
        User user = new User("john_doe", "password");

        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getBasket());
    }


    @Test
    public void testBasketAssociation() {
        Basket basket = new Basket();
        User user = new User("john_doe", "password", basket);

        assertEquals(basket, user.getBasket());
        // You can add more assertions or test cases as needed.
    }
}
