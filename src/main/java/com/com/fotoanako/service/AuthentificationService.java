package com.com.fotoanako.service;

import com.com.fotoanako.model.LoginForm;
import com.com.fotoanako.model.User;
import com.com.fotoanako.model.dto.AuthentificationResponse;
import com.com.fotoanako.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;


  public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthentificationResponse login(LoginForm form) {

    User user = userRepository.findByEmail(form.email())
        .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

    if (!passwordEncoder.matches(form.password(), user.getPassword())) {

      throw new BadCredentialsException("Invalid email or password");
    }
    String token = jwtService.generateToken(user);
    return AuthentificationResponse.builder()
        .token(token)
        .email(user.getEmail())
        .role(user.getRole())
        .build();
  }
}
