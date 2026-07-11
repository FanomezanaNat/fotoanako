package com.com.fotoanako.model.dto;

import lombok.Builder;
import com.com.fotoanako.model.UserRole;

@Builder
public record AuthentificationResponse(
    String token,
    String email,
    UserRole role
) {

}
