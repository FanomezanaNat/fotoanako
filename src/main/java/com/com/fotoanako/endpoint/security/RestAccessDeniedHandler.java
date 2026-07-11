package com.com.fotoanako.endpoint.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import com.com.fotoanako.model.dto.ErrorResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {


  private final ObjectMapper objectMapper;

  public RestAccessDeniedHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      @NonNull AccessDeniedException accessDeniedException) throws IOException {
    var username = Objects.requireNonNull(SecurityContextHolder.getContext()
            .getAuthentication())
        .getName();
    log.warn("403 on {} {}: advisor={}", request.getMethod(), request.getRequestURI(), username);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter()
        .write(objectMapper.writeValueAsString(
            new ErrorResponse("FORBIDDEN", "Insufficient role for this operation")));
  }
}
