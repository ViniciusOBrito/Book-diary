package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_BOOKSHELF")
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "category", nullable = false, unique = true)
    private Category category;

    @OneToMany
    @JoinColumn(name = "bookshelf_id")
    private List<Book> books;
}
