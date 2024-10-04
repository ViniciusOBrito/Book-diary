package com.brito.bookdiary.auth;

import com.brito.bookdiary.auth.dto.LoginRequestDTO;
import com.brito.bookdiary.auth.dto.RegisterUserRequestDTO;
import com.brito.bookdiary.role.Role;
import com.brito.bookdiary.security.TokenService;
import com.brito.bookdiary.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuthService authService;
    @MockBean
    PasswordEncoder passwordEncoder;
    @MockBean
    TokenService tokenService;
    @MockBean
    UserService userService;

    private LoginRequestDTO loginRequestDTO;
    private RegisterUserRequestDTO registerUserRequestDTO;

    @BeforeEach
    void setUp(){
        loginRequestDTO = new LoginRequestDTO("vinicius@example.com", "senha");
        registerUserRequestDTO = new RegisterUserRequestDTO("vinicus", "vinicius@example.com", "senha", "98080-8080", new Role());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    @DisplayName("Should return a token respose DTO with token when login is successful")
    public void login_ReturnsResponseDTO_WhenSuccessful() throws Exception {
        String json = objectMapper.writeValueAsString(loginRequestDTO);

        mockMvc
                .perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    @DisplayName("Should register a new user and return a TokenResposeDTO with a valid token")
    public void register_SaveNewUser_ReturnsToken() throws Exception {
        String json = objectMapper.writeValueAsString(registerUserRequestDTO);

        mockMvc
                .perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}