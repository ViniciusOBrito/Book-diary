package com.brito.bookdiary.publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Publisher Request DTO", description = "DTO containing infos about the publisher")
public record PublisherRequestDTO(

        @Schema(description = "Name of the publisher", example = "Grupo Editorial Record")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(description = "Email of the publisher", example = "comercial@record.com.br")
        @NotBlank(message = "Email is required")
        String email
){
}
