package mu.mcb.fotoanako.model.dto;

import lombok.Builder;
import mu.mcb.fotoanako.model.UserRole;

@Builder
public record AuthentificationResponse(
    String token,
    String email,
    UserRole role
) {

}
