package com.epitech.server.controller;

import com.epitech.server.model.User;
import com.epitech.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody User user) {
    String userId = authService.loginUser(user.getUsername(), user.getPassword());
    if (userId != null) {
      return new ResponseEntity<>(userId, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Couldn't log in",HttpStatus.BAD_REQUEST);
    }
  }
}
