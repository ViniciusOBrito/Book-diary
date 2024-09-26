package com.brito.bookdiary.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Token Responde DTO", description = "DTO to return infos about the token generated.")
public record TokenResponseDTO(
        @Schema(description = "Token provided in the login/register", example = "eyJhbGciOiJIUzI1NiIsInR....")
        String token,
        @Schema(description = "Expiration time of the token", example = "2024-09-25T23:51:46.774942800Z")
        String expireAt,
        @Schema(description = "Name of the user that generated token", example = "vinicius")
        String name
){
}
