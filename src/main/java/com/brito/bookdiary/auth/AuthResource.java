package com.brito.bookdiary.auth;

import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.auth.dto.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth Controller")
public interface AuthResource {


    @Operation(summary = "User login", description = "Login a user to generate a token access")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<TokenResponseDTO> loginUser(@Valid @Parameter(description = "Login credentials of the user", required = true) LoginRequestDTO dto);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid details"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Operation(summary = "Register a user", description = "Register a new user and generate a token access")
    ResponseEntity<TokenResponseDTO> registerUser(@Valid @Parameter(description = "Details for registering a new user", required = true) RegisterUserRequestDTO dto);

}
