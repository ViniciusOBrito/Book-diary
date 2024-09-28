package com.brito.bookdiary.user;

import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TAB_USER")
@Entity
public class User implements UserDetails {

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
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "userAuthor")
    @JsonIgnore
    private List<Post> posts;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
