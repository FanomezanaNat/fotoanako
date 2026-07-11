package com.com.fotoanako.endpoint.rest.controller;

import jakarta.validation.Valid;
import com.com.fotoanako.model.LoginForm;
import com.com.fotoanako.model.dto.AuthentificationResponse;
import com.com.fotoanako.model.dto.UserRequest;
import com.com.fotoanako.model.dto.UserResponse;
import com.com.fotoanako.service.AuthentificationService;
import com.com.fotoanako.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

  private final AuthentificationService authService;
  private final UserService userService;

  public AuthentificationController(AuthentificationService authService, UserService userService) {
    this.authService = authService;
    this.userService = userService;
  }

  @PostMapping("/register")
  public UserResponse register(@Valid @RequestBody UserRequest request) {
    return userService.register(request);
  }

  @PostMapping("/login")
  public AuthentificationResponse login(@Valid @RequestBody LoginForm form) {

    return authService.login(form);
  }

}
