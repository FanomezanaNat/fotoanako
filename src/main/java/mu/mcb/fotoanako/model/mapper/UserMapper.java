package mu.mcb.fotoanako.model.mapper;

import mu.mcb.fotoanako.model.User;
import mu.mcb.fotoanako.model.dto.UserResponse;

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
