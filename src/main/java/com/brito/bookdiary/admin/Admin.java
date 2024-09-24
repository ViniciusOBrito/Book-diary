package com.brito.bookdiary.admin;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_ADMIN")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", unique = true, length = 35, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
}
