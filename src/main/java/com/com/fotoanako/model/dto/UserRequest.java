package com.com.fotoanako.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @Size(max = 100) String lastName,
    @Size(max = 100) String firstName,
    @NotBlank @Email String email,
    @Size(max = 100) @NotBlank String password
) {

}
