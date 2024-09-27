package com.brito.bookdiary.role;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_ROLE")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;

    public Role() {

    }

    @Override
    public String getAuthority() {
        return name;
    }

    public Role(String name){
        this.name = name;
    }
}
