package com.brito.bookdiary.post;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private User userAuthor;

    @ManyToOne
    private Book book;

    @Column(name = "comment", nullable = false, length = 255)
    private String comment;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "from_page", nullable = false)
    private Long fromPage;

    @Column(name = "to_the_page", nullable = false)
    private Long toThePage;
}
