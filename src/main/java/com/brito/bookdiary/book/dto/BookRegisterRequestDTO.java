package com.brito.bookdiary.book.dto;

import java.util.UUID;

public record BookRegisterRequestDTO(
        String title,
        String category,
        UUID authorID,
        UUID publisherID,
        Long numberOfPages
) {
}
