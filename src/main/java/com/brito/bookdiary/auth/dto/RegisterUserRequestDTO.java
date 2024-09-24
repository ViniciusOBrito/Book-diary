package com.brito.bookdiary.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestDTO (
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Phone is required")
        String phone
){
}
