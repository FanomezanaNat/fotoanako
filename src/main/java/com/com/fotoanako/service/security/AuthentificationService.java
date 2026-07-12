package com.com.fotoanako.service.security;

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
  private final BlackListTokenService blackListTokenService;


  public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService, BlackListTokenService blackListTokenService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.blackListTokenService = blackListTokenService;
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

  public void logout(String authHeader) {

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }

    String token = authHeader.substring(7)
        .trim();

    jwtService.parseClaims(token); // Throws JwtException if invalid

    blackListTokenService.blacklist(token);
  }
}
