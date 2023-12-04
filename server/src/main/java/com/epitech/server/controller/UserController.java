package com.epitech.server.controller;

import com.epitech.server.model.User;
//import com.epitech.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/users")
public class UserController  {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> accounts = userService.getAllUsers();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @GetMapping("/{userID}")
//    public ResponseEntity<User> getUserById(@PathVariable String userID ) {
//        User user = userService.findById(userID);
//
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return user;
//    }
}
