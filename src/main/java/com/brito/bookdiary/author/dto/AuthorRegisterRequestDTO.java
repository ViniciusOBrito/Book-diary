package com.brito.bookdiary.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Author Register Request DTO", description = "DTO for registering a new author")
public record AuthorRegisterRequestDTO (
        @Schema(description = "Name of the new author", example = "Albert Camus")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Email of the new author", example = "cammus@example.com")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(description = "Date of the birth of the new author", example = "17/11/1913")
        @NotBlank(message = "DateOfBirth is required")
        String dateOfBirth
){
}
