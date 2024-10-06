package com.brito.bookdiary.user;

import com.brito.bookdiary.role.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class MockUserFactory {
    public static User mockUser(){
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Name");
        user.setEmail("name@example.com");
        user.setPhone("9199999999");
        user.setPassword("senha");
        user.setRoles(Collections.singleton(new Role()));
        user.setPosts(new ArrayList<>());

        return user;
    }

}
