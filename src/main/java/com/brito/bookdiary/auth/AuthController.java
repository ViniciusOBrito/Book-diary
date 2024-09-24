package com.brito.bookdiary.auth;

import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterAdminRequestDTO;
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


    @PostMapping("/user/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody @Valid LoginRequestDTO dto){
        return ResponseEntity.ok(authService.loginUser(dto));
    }

    @PostMapping("user/register")
    public ResponseEntity<TokenResponseDTO> registerUser(@RequestBody @Valid RegisterUserRequestDTO dto){
        return ResponseEntity.ok(authService.registerUser(dto));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<TokenResponseDTO> loginAdmin(@RequestBody @Valid LoginRequestDTO dto){
        return ResponseEntity.ok(authService.loginAdmin(dto));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<TokenResponseDTO> registerAdmin(@RequestBody @Valid RegisterAdminRequestDTO dto){
        return ResponseEntity.ok(authService.registerAdmin(dto));
    }

}
