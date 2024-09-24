package com.brito.bookdiary.author;

import com.brito.bookdiary.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", length = 35, nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
