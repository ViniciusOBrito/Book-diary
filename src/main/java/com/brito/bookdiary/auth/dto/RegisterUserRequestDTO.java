package com.brito.bookdiary.auth.dto;

import com.brito.bookdiary.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Register User Request DTO", description = "DTO for registering a new user")
public record RegisterUserRequestDTO (
        @Schema(description = "Name of the new user", example = "vinicius brito")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(description = "Email of the new user", example = "vinicius.admin@example.com")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(description = "Password of the new user", example = "12345")
        @NotBlank(message = "Password is required")
        String password,
        @Schema(description = "Phone of the new user", example = "98080-8080")
        @NotBlank(message = "Phone is required")
        String phone,
        @Schema(description = "Role of the new user", example = "ADMIN")
        @NotNull(message = "Role is required")
        Role Role
){
}
