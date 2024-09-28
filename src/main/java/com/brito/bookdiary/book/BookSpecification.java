package com.brito.bookdiary.book;

import com.brito.bookdiary.post.Post;
import jakarta.persistence.criteria.Join;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSpecification {

    public static Specification<Book> booksByUser(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            Join<Book, Post> postJoin = root.join("posts");
            return criteriaBuilder.equal(postJoin.get("userAuthor").get("id"), userId);
        };
    }
}
