package com.brito.bookdiary.post.dto;

import java.util.UUID;

public record PostRequestDTO(
        UUID bookId,
        String comment,
        Long fromPage,
        Long toThePage
) {
}
