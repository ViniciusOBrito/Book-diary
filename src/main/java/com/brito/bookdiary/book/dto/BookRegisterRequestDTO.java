package com.brito.bookdiary.book.dto;

import com.brito.bookdiary.bookshelf.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Schema(name = "Book Register Request DTO", description = "DTO for registering a new book")
public record BookRegisterRequestDTO(

        @Schema(description = "Title of the book", example = "The outsider")
        @NotBlank(message = "Title is required")
        String title,
        @Schema(description = "Category of the book", example = "ROMANCE")
        @NotBlank(message = "Category is required")
        Category category,
        @Schema(description = "Id of the author of the book", example = "3278f27a394b4ee7ac4f....")
        @NotBlank(message = "authorID is required")
        UUID authorID,
        @Schema(description = "Id of the publisher of the book", example = "1076ac4605a644088b....")
        @NotBlank(message = "publisherID is required")
        UUID publisherID,
        @Schema(description = "Number of the pages that have in the book", example = "230")
        @NotBlank(message = "numberOfPages is required")
        Long numberOfPages
) {
}
