package com.brito.bookdiary.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TAB_USER")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email",nullable = false, length = 35, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone", unique = true)
    private String phone;
}
