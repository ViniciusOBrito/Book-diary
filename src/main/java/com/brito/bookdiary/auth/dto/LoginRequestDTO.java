package com.brito.bookdiary.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Login Request DTO", description = "DTO for login request")
public record LoginRequestDTO(
        @Schema(description = "Email of the user", example = "vinicius@example.com")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(description = "Password of the user", example = "12345")
        @NotBlank(message = "Password is required")
        String password
) {
}
