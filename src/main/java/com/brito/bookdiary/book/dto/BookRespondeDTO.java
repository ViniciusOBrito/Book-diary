package com.brito.bookdiary.book.dto;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;

import java.util.List;
import java.util.UUID;

public record BookRespondeDTO(
        UUID id,
        String name,
        Category category,
        Author author,
        Publisher publisher,
        Long numberOfPages,
        List<Post> posts
) {

    public BookRespondeDTO(Book book){
        this(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor(),
                book.getPublisher(),
                book.getNumberOfPages(),
                book.getPosts()
        );
    }
}
