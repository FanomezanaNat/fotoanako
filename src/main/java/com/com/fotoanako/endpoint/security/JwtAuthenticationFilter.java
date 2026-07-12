package com.com.fotoanako.endpoint.security;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import com.com.fotoanako.service.security.BlackListTokenService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.com.fotoanako.service.security.JwtService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final BlackListTokenService blackListTokenService;

  public JwtAuthenticationFilter(JwtService jwtService, BlackListTokenService blackListTokenService) {
    this.jwtService = jwtService;
    this.blackListTokenService = blackListTokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    var header = request.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    var token = header.substring(7);
    if (blackListTokenService.isBlacklisted(token)) {
      response.sendError(SC_UNAUTHORIZED,
          "Token has been revoked");
      return;
    }
    try {
      var claims = jwtService.parseClaims(token);
      var email = claims.getSubject();

      if (email != null && SecurityContextHolder.getContext()
          .getAuthentication() == null) {
        String role = claims.get("role", String.class);
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

        UserDetails userDetails = new User(email, "", authorities);

        var authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext()
            .setAuthentication(authentication);
      }
    } catch (JwtException ex) {
      log.debug("JWT token rejected: {}", ex.getMessage());
    }

    filterChain.doFilter(request, response);
  }
}
