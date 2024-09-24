package com.brito.bookdiary.post.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.user.User;

import java.time.LocalDateTime;

public record PostRespondeDTO(
        User userAuthor,
        Book book,
        String comment,
        LocalDateTime timestamp,
        Long fromPage,
        Long toThePage
) {

    public PostRespondeDTO(Post post){
        this(
                post.getUserAuthor(),
                post.getBook(),
                post.getComment(),
                post.getTimestamp(),
                post.getFromPage(),
                post.getToThePage()
        );
    }
}
