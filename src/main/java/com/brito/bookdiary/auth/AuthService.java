package com.brito.bookdiary.auth;
import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.auth.dto.TokenResponseDTO;
import com.brito.bookdiary.exception.InvalidCredentialException;
import com.brito.bookdiary.security.TokenService;
import com.brito.bookdiary.user.User;
import com.brito.bookdiary.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO loginUser(LoginRequestDTO dto){

        User user = userService.findOrThrow(dto.email());

        if(passwordEncoder.matches(dto.password(), user.getPassword())){
            String token = tokenService.generateToken(user);
            Instant expireAt = tokenService.generateExpirationInstant();
            return new TokenResponseDTO(token, expireAt.toString(), user.getName());
        }

        throw new InvalidCredentialException();
    }

    public TokenResponseDTO registerUser(RegisterUserRequestDTO dto){

        User user = userService.createUser(dto, passwordEncoder);

        String token = tokenService.generateToken(user);
        Instant expireAt = tokenService.generateExpirationInstant();

        return new TokenResponseDTO(token, expireAt.toString(), user.getName());
    }

}
