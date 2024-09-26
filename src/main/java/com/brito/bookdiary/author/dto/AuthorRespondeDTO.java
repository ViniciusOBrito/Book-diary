package com.brito.bookdiary.author.dto;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.Book;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Schema(name = "Author Responde DTO", description = "DTO for the responde containing infos about the author")
public record AuthorRespondeDTO(
        @Schema(description = "Id of the author", example = "3278f27a394b4ee....")
        UUID id,
        @Schema(description = "Name of the author", example = "Albert Camus")
        String name,
        @Schema(description = "Date of the birth of the author", example = "1913-01-07 00:11:00.000000")
        Date dateOfBirth,
        @Schema(description = "Books that are associated to that author", example = "The outsider, The plague")
        List<Book> books
) {

    public AuthorRespondeDTO(Author author){
        this(
                author.getId(),
                author.getName(),
                author.getDateOfBirth(),
                author.getBooks()
        );
    }
}
