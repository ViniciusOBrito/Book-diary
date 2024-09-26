package com.brito.bookdiary.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Schema(name = "Post Request DTO", description = "DTO for registering a new post")
public record PostRequestDTO(
        @Schema(description = "Email of the user", example = "vinicius@example.com")
        @NotBlank(message = "Email of the user is required")
        String userEmail,
        @Schema(description = "Id of the book that is the post about", example = "3278f27a394....")
        @NotBlank(message = "bookId is required")
        UUID bookId,
        @Schema(description = "Comment the user is making about the book.", example = "I dont beleave that they made that..")
        @NotBlank(message = "comment is required")
        String comment,
        @Schema(description = "Inicial page that the comment is about", example = "10")
        @NotBlank(message = "fromPage is required")
        Long fromPage,
        @Schema(description = "Final page that the comment is about", example = "11")
        @NotBlank(message = "toThePage is required")
        Long toThePage
) {
}
