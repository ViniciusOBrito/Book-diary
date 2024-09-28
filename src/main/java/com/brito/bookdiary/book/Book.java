package com.brito.bookdiary.book;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "number_of_pages", nullable = false)
    private Long numberOfPages;

    @ManyToOne
    @JsonManagedReference
    private Author author;

    @ManyToOne
    @JsonManagedReference
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        if(isNull(post)){
            posts = new ArrayList<>();
        }
        posts.add(post);
    }
}
