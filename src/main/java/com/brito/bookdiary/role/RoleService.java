package com.brito.bookdiary.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findOrCreate(String name){

        Role role = roleRepository.findByName(name).orElse(null);

        if (nonNull(role)){
            return role;
        }

        return roleRepository.save(new Role(name));
    }
}
