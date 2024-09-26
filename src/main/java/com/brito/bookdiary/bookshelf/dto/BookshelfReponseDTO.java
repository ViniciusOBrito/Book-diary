package com.brito.bookdiary.bookshelf.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.bookshelf.Bookshelf;
import com.brito.bookdiary.bookshelf.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Bookshelf Responde DTO", description = "DTO of the responde containing infos about the bookshelf")
public record BookshelfReponseDTO(
        @Schema(description = "Category of the bookshelf", example = "ROMANCE")
        Category category,
        @Schema(description = "Books that have the same category of the bookshelf")
        List<Book> books
) {

    public BookshelfReponseDTO(Bookshelf bookshelf){
        this(
                bookshelf.getCategory(),
                bookshelf.getBooks()
        );
    }
}
