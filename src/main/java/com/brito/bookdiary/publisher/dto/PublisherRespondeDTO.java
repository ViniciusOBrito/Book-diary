package com.brito.bookdiary.publisher.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.publisher.Publisher;

import java.util.List;

public record PublisherRespondeDTO(
        String name,
        String email,
        List<Book> books
) {

    public PublisherRespondeDTO(Publisher publisher){
        this(
                publisher.getName(),
                publisher.getEmail(),
                publisher.getBooks()
        );
    }
}
