package com.brito.bookdiary.author.dto;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.Book;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record AuthorRespondeDTO(
        UUID id,
        String name,
        Date dateOfBirth,
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
