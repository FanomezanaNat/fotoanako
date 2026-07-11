package com.com.fotoanako.endpoint.rest.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.com.fotoanako.model.dto.AgendaRequest;
import com.com.fotoanako.model.dto.AgendaResponse;
import com.com.fotoanako.service.AgendaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

  private final AgendaService service;

  public AgendaController(AgendaService service) {
    this.service = service;
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @PostMapping
  public AgendaResponse create(Authentication authenticated, @RequestBody AgendaRequest request) {

    return service.create(authenticated, request);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public Page<AgendaResponse> findAll(
      @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

    return service.findAll(pageable);
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/my-agenda")
  public AgendaResponse getUserAgenda(Authentication authenticated) {

    return service.getUserAgenda(authenticated);
  }


  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @DeleteMapping("/my-agenda")
  @ResponseStatus(NO_CONTENT)
  public void delete(Authentication authenticated) {

    service.delete(authenticated);
  }
}
