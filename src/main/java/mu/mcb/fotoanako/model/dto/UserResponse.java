package mu.mcb.fotoanako.model.dto;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String lastName, String firstName, String email) {

}
