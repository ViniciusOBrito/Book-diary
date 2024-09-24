package com.brito.bookdiary.auth;

import com.brito.bookdiary.admin.Admin;
import com.brito.bookdiary.admin.AdminService;
import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterAdminRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.auth.dto.TokenResponseDTO;
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
    private final AdminService adminService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO loginUser(LoginRequestDTO dto){

        User user = userService.findOrThrow(dto.email());

        if(passwordEncoder.matches(dto.password(), user.getPassword())){
            String token = tokenService.generateUserToken(user);
            Instant expireAt = tokenService.generateExpirationInstant();
            return new TokenResponseDTO(token, expireAt.toString(), user.getName());
        }

        //trocar por uma exception dedicada
        throw new RuntimeException("Credentials invalid");
    }

    public TokenResponseDTO registerUser(RegisterUserRequestDTO dto){

        userService.validateUserAlreadyExist(dto.email());

        User user = userService.createUser(dto, passwordEncoder);

        String token = tokenService.generateUserToken(user);
        Instant expireAt = tokenService.generateExpirationInstant();

        return new TokenResponseDTO(token, expireAt.toString(), user.getName());
    }

    public TokenResponseDTO loginAdmin(LoginRequestDTO dto){

        Admin admin = adminService.findOrThrow(dto.email());

        if (passwordEncoder.matches(dto.password(), admin.getPassword())){
            String token = tokenService.generateAdminToken(admin);
            Instant expireAt = tokenService.generateExpirationInstant();

            return new TokenResponseDTO(token, expireAt.toString(), admin.getName());
        }

        throw new RuntimeException("Credentials invalid");
    }

    public TokenResponseDTO registerAdmin(RegisterAdminRequestDTO dto){

        adminService.validateAdminAlreadyExist(dto.email());

        Admin admin = adminService.createAdmin(dto, passwordEncoder);

        String token = tokenService.generateAdminToken(admin);
        Instant expireAt = tokenService.generateExpirationInstant();

        return new TokenResponseDTO(token, expireAt.toString(), admin.getName());
    }
}
