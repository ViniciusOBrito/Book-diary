package com.brito.bookdiary.bookshelf.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.bookshelf.Bookshelf;
import com.brito.bookdiary.bookshelf.Category;

import java.util.List;

public record BookshelfReponseDTO(
        Category category,
        List<Book> books
) {

    public BookshelfReponseDTO(Bookshelf bookshelf){
        this(
                bookshelf.getCategory(),
                bookshelf.getBooks()
        );
    }
}
