package com.brito.bookdiary.user;

import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;


    public User findOrThrow(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found."));
    }

    public void validateUserAlreadyExist(String email){
        userRepository.findByEmail(email)
                .ifPresent(user -> new RuntimeException("User with email "  + email +" already exist."));
    }

    public User createUser(RegisterUserRequestDTO dto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setPhone(dto.phone());

        return userRepository.save(user);
    }

}
