package com.brito.bookdiary.user;

import com.brito.bookdiary.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    @JsonIgnore
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email",nullable = false, length = 35, unique = true)
    private String email;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    @JsonIgnore
    @Column(name = "phone", unique = true)
    private String phone;
    @OneToMany(mappedBy = "userAuthor")
    @JsonIgnore
    private List<Post> posts;
}
