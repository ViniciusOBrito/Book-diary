package com.brito.bookdiary.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Register Admin Request DTO", description = "DTO for registering a new admin")
public record RegisterAdminRequestDTO (
        @Schema(description = "Name of the new admin", example = "vinicius brito")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(description = "Email of the new admin", example = "vinicius.admin@example.com")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(description = "Password of the new admin", example = "12345")
        @NotBlank(message = "Password is required")
        String password
){
}
