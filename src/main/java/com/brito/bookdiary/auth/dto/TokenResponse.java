package com.brito.bookdiary.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@AllArgsConstructor
@Data
public class TokenResponse {

    private String token;
    private Date expiresIn;
    private String username;
}
