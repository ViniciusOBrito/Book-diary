package com.brito.bookdiary.book;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "TAB_BOOk")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "number_of_pages", nullable = false)
    private Long numberOfPages;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        if(isNull(post)){
            posts = new ArrayList<>();
        }
        posts.add(post);
    }
}
