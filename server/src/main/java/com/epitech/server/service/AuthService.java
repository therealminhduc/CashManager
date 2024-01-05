package com.epitech.server.service;

import com.epitech.server.model.User;
import com.epitech.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
  @Autowired
  UserRepository userRepository;

  public String loginUser(String username, String password) {
    User user = userRepository.findUserByUsername(username).orElse(null);
    if (user == null) {
      return null;
    }
    String storedPassword = user.getPassword();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    if (!encoder.matches(password, storedPassword)) {
      return null;
    }
    return user.getId();
  }
}
