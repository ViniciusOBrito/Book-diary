package com.brito.bookdiary.publisher;

import com.brito.bookdiary.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_PUBLISHER")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
