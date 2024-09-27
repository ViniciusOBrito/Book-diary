package com.brito.bookdiary.bookshelf.dto;

import com.brito.bookdiary.bookshelf.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Bookshelf Request DTO", description = "DTO for registering a new bookshelf")
public record BookshelfRequestDTO (
        @Schema(description = "Categoy of the new bookshelf", example = "HORROR")
        @NotNull(message = "Category is required")
        Category category
){
}
