package com.brito.bookdiary.auth;

import com.brito.bookdiary.auth.dto.TokenResponseDTO;
import com.brito.bookdiary.exception.InvalidCredentialException;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.security.TokenService;
import com.brito.bookdiary.user.User;
import com.brito.bookdiary.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;

import static com.brito.bookdiary.auth.mock.MockAuthFactory.*;
import static com.brito.bookdiary.user.MockUserFactory.mockUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    TokenService tokenService;
    @Mock
    UserService userService;
    @InjectMocks
    private AuthService authService;

    private User user;

    @BeforeEach
    void setUp(){
        user = mockUser();
    }


    @Test
    @DisplayName("Should return a TokenResponseDTO when login is successful")
    void loginUser_ReturnsTokenResponseDTO_WhenSuccessful(){

        when(userService.findOrThrow(any())).thenReturn(user);
        when(passwordEncoder.matches(mockLoginRequestDTO().password(), user.getPassword())).thenReturn(true);

        Instant expiration = Instant.now().plusSeconds(3600);

        when(tokenService.generateToken(user)).thenReturn(mockToken());
        when(tokenService.generateExpirationInstant()).thenReturn(expiration);

        TokenResponseDTO result = authService.loginUser(mockLoginRequestDTO());

        assertNotNull(result);
        assertEquals(user.getName(), result.name());
        assertEquals(mockToken(), result.token());
        assertEquals(expiration.toString(), result.expireAt());
    }

    @Test
    @DisplayName("Should register a new user when valid data and return a TokenRespondeDTO")
    void registerUser_ReturnsTokenResponseDTO_WhenSuccessful(){
        Instant expiration = Instant.now().plusSeconds(3600);

        when(userService.createUser(mockRegisterUserRequestDTO(), passwordEncoder)).thenReturn(user);

        when(tokenService.generateToken(user)).thenReturn(mockToken());
        when(tokenService.generateExpirationInstant()).thenReturn(expiration);

        TokenResponseDTO result = authService.registerUser(mockRegisterUserRequestDTO());

        assertNotNull(result);
        assertEquals(mockUser().getName(), result.name());
        assertEquals(mockToken(), result.token());
        assertEquals(expiration.toString(), result.expireAt());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when login user with non-existing email")
    void loginUser_ReturnsResourceNotFound_WhenUserDoesNotExist(){
        when(userService.findOrThrow(any())).thenThrow(new ResourceNotFoundException("User not found."));

        assertThrows(ResourceNotFoundException.class, () -> {
            authService.loginUser(mockLoginRequestDTO());
        });
    }

    @Test
    @DisplayName("Should throw ResourceAlreadyExist when register a user with a email that is already registered")
    void registerUser_ReturnsResourceAlreadyExist_WhenEmailAlreadyRegistered(){
        when(userService.createUser(mockRegisterUserRequestDTO(), passwordEncoder)).thenThrow(new ResourceAlreadyExistException("User already exist."));

        assertThrows(ResourceAlreadyExistException.class, () -> {
            authService.registerUser(mockRegisterUserRequestDTO());
        });
    }

    @Test
    @DisplayName("Should throw InvalidCredentialException when login user with incorrect password")
    void loginUser_ReturnsInvalidCredentialException_WhenPasswordIsIncorrect(){
        when(userService.findOrThrow(any())).thenReturn(user);
        when(passwordEncoder.matches(mockLoginRequestDTO().password(), user.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialException.class ,() ->{
            authService.loginUser(mockLoginRequestDTO());
        });
    }



}