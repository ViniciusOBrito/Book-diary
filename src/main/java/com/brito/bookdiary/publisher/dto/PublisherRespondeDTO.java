package com.brito.bookdiary.publisher.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.publisher.Publisher;

import java.util.List;
import java.util.UUID;

public record PublisherRespondeDTO(
        UUID id,
        String name,
        String email,
        List<Book> books
) {

    public PublisherRespondeDTO(Publisher publisher){
        this(
                publisher.getId(),
                publisher.getName(),
                publisher.getEmail(),
                publisher.getBooks()
        );
    }
}
