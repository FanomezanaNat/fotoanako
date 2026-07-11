package com.com.fotoanako.endpoint.security;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import com.com.fotoanako.model.dto.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  public RestAuthenticationEntryPoint(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    log.warn("401 on {} {}: {}", request.getMethod(), request.getRequestURI(),
        authException.getMessage());
    response.setStatus(SC_UNAUTHORIZED);
    response.setContentType(APPLICATION_JSON_VALUE);
    response.getWriter()
        .write(objectMapper.writeValueAsString(
            new ErrorResponse("UNAUTHORIZED", "Authentication required or token invalid")));
  }
}
