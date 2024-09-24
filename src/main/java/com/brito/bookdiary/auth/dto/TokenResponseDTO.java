package com.brito.bookdiary.auth.dto;


public record TokenResponseDTO(
        String token,
        String expireAt,
        String name
){
}
