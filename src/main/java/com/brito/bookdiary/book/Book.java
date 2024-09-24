package com.brito.bookdiary.book;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_BOOk")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String category;

    private Long numberOfPages;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<Post> posts;

}
