package com.brito.bookdiary.book.dto;

import com.brito.bookdiary.bookshelf.Category;

import java.util.UUID;

public record BookRegisterRequestDTO(
        String title,
        Category category,
        UUID authorID,
        UUID publisherID,
        Long numberOfPages
) {
}
