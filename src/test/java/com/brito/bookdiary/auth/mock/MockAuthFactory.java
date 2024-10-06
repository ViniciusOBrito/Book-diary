package com.brito.bookdiary.auth.mock;

import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.auth.dto.TokenResponseDTO;
import com.brito.bookdiary.role.Role;

import java.time.Instant;

public class MockAuthFactory {

    public static LoginRequestDTO mockLoginRequestDTO(){
        return new LoginRequestDTO(
                "example@example.com",
                "senha"
        );
    }

    public static RegisterUserRequestDTO mockRegisterUserRequestDTO(){
        return new RegisterUserRequestDTO(
                "Name",
                "example@example.com",
                "senha",
                "9199999999",
                new Role()
        );
    }

    public static String mockToken(){
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp...";
    }
}
