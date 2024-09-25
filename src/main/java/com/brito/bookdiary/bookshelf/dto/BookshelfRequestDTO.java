package com.brito.bookdiary.bookshelf.dto;

import com.brito.bookdiary.book.Book;

import java.util.List;

public record BookshelfRequestDTO (
        String category,
        List<Book> books
){
}
