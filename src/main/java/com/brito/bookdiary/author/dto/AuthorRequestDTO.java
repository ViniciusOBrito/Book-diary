package com.brito.bookdiary.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Author Request DTO", description = "DTO containing infos about a author")
public record AuthorRequestDTO(
        @Schema(description = "Name of author", example = "Albert Camus")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Email of author", example = "cammus@example.com")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(description = "Date of the birth of author", example = "17/11/1913")
        @NotBlank(message = "DateOfBirth is required")
        String dateOfBirth
){
}
