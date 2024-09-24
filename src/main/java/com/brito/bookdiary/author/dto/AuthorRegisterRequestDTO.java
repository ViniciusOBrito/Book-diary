package com.brito.bookdiary.author.dto;

public record AuthorRegisterRequestDTO (
        String name,
        String email,
        String dateOfBirth
){
}
