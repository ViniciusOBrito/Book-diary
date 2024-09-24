package com.brito.bookdiary.admin;

import com.brito.bookdiary.auth.dto.RegisterAdminRequestDTO;
import com.brito.bookdiary.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final TokenService tokenService;

    public Admin findOrThrow(String email){
        return adminRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Admin not found."));
    }

    public void validateAdminAlreadyExist(String email){
        adminRepository.findByEmail(email)
                .ifPresent(admin -> new RuntimeException("Admin with " + email + " already exist."));
    }

    public Admin createAdmin(RegisterAdminRequestDTO dto, PasswordEncoder passwordEncoder){
        Admin admin = new Admin();
        admin.setName(dto.name());
        admin.setEmail(dto.email());
        admin.setPassword(passwordEncoder.encode(dto.password()));

        return adminRepository.save(admin);
    }
}
