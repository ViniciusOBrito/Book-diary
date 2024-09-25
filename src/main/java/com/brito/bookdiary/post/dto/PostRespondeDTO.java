package com.brito.bookdiary.post.dto;

import com.brito.bookdiary.post.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostRespondeDTO(
        UUID userAuthorId,
        String userAuthorEmail,
        UUID bookId,
        String comment,
        LocalDateTime timestamp,
        Long fromPage,
        Long toThePage
) {

    public PostRespondeDTO(Post post){
        this(
                post.getUserAuthor().getId(),
                post.getUserAuthor().getEmail(),
                post.getBook().getId(),
                post.getComment(),
                post.getTimestamp(),
                post.getFromPage(),
                post.getToThePage()
        );
    }
}
