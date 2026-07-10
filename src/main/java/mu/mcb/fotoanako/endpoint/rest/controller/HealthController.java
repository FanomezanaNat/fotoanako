package mu.mcb.fotoanako.endpoint.rest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/whoami")
  public String getCurrentUser(Authentication authentication) {
    return authentication.getName();
  }

}
