package mu.mcb.fotoanako.endpoint.rest.controller;

import jakarta.validation.Valid;
import mu.mcb.fotoanako.model.LoginForm;
import mu.mcb.fotoanako.model.dto.AuthentificationResponse;
import mu.mcb.fotoanako.model.dto.UserRequest;
import mu.mcb.fotoanako.model.dto.UserResponse;
import mu.mcb.fotoanako.service.AuthentificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

  private final AuthentificationService service;

  public AuthentificationController(AuthentificationService service) {
    this.service = service;
  }

  @PostMapping("/register")
  public UserResponse register(@Valid @RequestBody UserRequest request) {
    return service.register(request);
  }

  @PostMapping("/login")
  public AuthentificationResponse login(@Valid @RequestBody LoginForm form) {

    return service.login(form);
  }

}
