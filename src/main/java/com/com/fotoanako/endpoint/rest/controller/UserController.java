package com.com.fotoanako.endpoint.rest.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.com.fotoanako.model.dto.UserRequest;
import com.com.fotoanako.model.dto.UserResponse;
import com.com.fotoanako.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }


  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public Page<UserResponse> findAll(
      @PageableDefault(sort = "lastName", direction = Direction.ASC) Pageable pageable) {
    return service.findAll(pageable);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public UserResponse findOne(@PathVariable Long id) {
    return service.findById(id);
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PutMapping("/{id}")
  public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
    return service.update(id, request);
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
