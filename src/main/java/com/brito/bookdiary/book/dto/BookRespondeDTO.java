package com.brito.bookdiary.book.dto;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.publisher.Publisher;

public record BookRespondeDTO(
        String name,
        String category,
        Author author,
        Publisher publisher,
        Long numberOfPages
) {

    public BookRespondeDTO(Book book){
        this(
                book.getTitle(),
                book.getCategory(),
                book.getAuthor(),
                book.getPublisher(),
                book.getNumberOfPages()
        );
    }
}
