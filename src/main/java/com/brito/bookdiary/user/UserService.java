package com.brito.bookdiary.user;

import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.role.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public User findOrThrow(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User with email %s not found.", email)));
    }

    public User createUser(RegisterUserRequestDTO dto, PasswordEncoder passwordEncoder){

        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new ResourceAlreadyExistException("User already registered in system. Please make login.");
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setPhone(dto.phone());
        user.setRoles(Collections.singleton(roleService.findOrCreate(dto.Role().getName())));

        return userRepository.save(user);
    }

}
