package com.brito.bookdiary.publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Publisher Register Request DTO", description = "DTO for registering a new publisher")
public record PublisherRegisterRequestDTO (

        @Schema(description = "Name of the new publisher", example = "Grupo Editorial Record")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(description = "Email of the new publisher", example = "comercial@record.com.br")
        @NotBlank(message = "Email is required")
        String email
){
}
