package com.brito.bookdiary.book.dto;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "Book Response DTO", description = "DTO for the responde containing infos about the book")
public record BookRespondeDTO(
        @Schema(description = "Id of the book", example = "b2ce01ce116b477d....")
        UUID id,
        @Schema(description = "Title of the book", example = "The outsider")
        String name,
        @Schema(description = "Category of the book", example = "ROMANCE")
        Category category,
        @Schema(description = "Author of the book")
        Author author,
        @Schema(description = "Publisher of the book")
        Publisher publisher,
        @Schema(description = "Number of pages that the book have", example = "230")
        Long numberOfPages,
        @Schema(description = "Post about this book")
        List<Post> posts
) {

    public BookRespondeDTO(Book book){
        this(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor(),
                book.getPublisher(),
                book.getNumberOfPages(),
                book.getPosts()
        );
    }
}
