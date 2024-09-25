package com.brito.bookdiary.bookshelf.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.bookshelf.Bookshelf;

import java.util.List;

public record BookshelfReponseDTO(
        String category,
        List<Book> books
) {

    public BookshelfReponseDTO(Bookshelf bookshelf){
        this(
                bookshelf.getCategory(),
                bookshelf.getBooks()
        );
    }
}
