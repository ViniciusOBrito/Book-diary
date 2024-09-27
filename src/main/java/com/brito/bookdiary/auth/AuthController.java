package com.brito.bookdiary.auth;

import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.auth.dto.TokenResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController implements AuthResource{

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody @Valid LoginRequestDTO dto){
        return ResponseEntity.ok(authService.loginUser(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> registerUser(@RequestBody @Valid RegisterUserRequestDTO dto){
        return ResponseEntity.ok(authService.registerUser(dto));
    }
}
