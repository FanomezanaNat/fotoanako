package com.com.fotoanako.model.mapper;

import com.com.fotoanako.model.User;
import com.com.fotoanako.model.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public static UserResponse toResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .lastName(user.getLastName())
        .firstName(user.getFirstName())
        .email(user.getEmail())
        .build();
  }

}
