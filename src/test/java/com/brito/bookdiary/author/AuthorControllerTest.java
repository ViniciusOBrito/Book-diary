package com.brito.bookdiary.author;

import com.brito.bookdiary.auth.AuthService;
import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.brito.bookdiary.author.mock.AuthorMockFactory.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TokenService tokenService;
    @MockBean
    AuthService authService;
    @MockBean
    UserService userService;
    @MockBean
    AuthorService authorService;

    private List<AuthorRespondeDTO> authorRespondeDTOList;

    private AuthorRequestDTO authorRequestDTO;

    @BeforeEach
    void setUp(){
        authorRespondeDTOList = mockListAuthorDTO();
        authorRequestDTO = mockRequestAuthorDTO();
    }

    @Test
    @WithMockUser(roles = {"USER","ADMIN"})
    @DisplayName("Should return a list of all authors")
    void getAllAuthors_ShouldReturnAuthorsList() throws Exception {
        when(authorService.getAllAuthors()).thenReturn(authorRespondeDTOList);

        mockMvc.perform(get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(authorRespondeDTOList.get(0).id().toString()))
                .andExpect(jsonPath("$[0].name").value(authorRespondeDTOList.get(0).name()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should create a new author and return Created")
    void createAuthor_WithValidData_ReturnsAuthorCreated() throws Exception {
        String authorJson = objectMapper.writeValueAsString(authorRequestDTO);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Shoud update a author and returns Ok")
    void updateAuthor_WithValidData_ReturnsOk() throws Exception{
        String authorJson = objectMapper.writeValueAsString(authorRequestDTO);

        mockMvc.perform(put("/api/authors/{authorId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

}