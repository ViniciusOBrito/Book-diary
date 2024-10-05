package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.auth.AuthService;
import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
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

import static com.brito.bookdiary.bookshelf.mock.mockBookshelfFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookshelfController.class)
class BookshelfControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TokenService tokenService;
    @MockBean
    AuthService authService;
    @MockBean
    BookshelfService bookshelfService;
    @MockBean
    UserService userService;

    private BookshelfReponseDTO bookshelfReponseDTO;
    private BookshelfRequestDTO bookshelfRequestDTO;
    private List<BookshelfReponseDTO> bookshelfReponseDTOList;

    @BeforeEach
    void setUp(){
        bookshelfReponseDTO = mockBookshelfResponseDTO();
        bookshelfRequestDTO = mockBookshelfRequestDTO();
        bookshelfReponseDTOList = mockListBookshelfResponseDTO();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("Should return a list off all bookshelf")
    void getAllBookshelf_ShouldReturnBookshelfList() throws Exception {
        when(bookshelfService.getAllBookShelf()).thenReturn(bookshelfReponseDTOList);

        mockMvc.perform(get("/api/bookshelves")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value(bookshelfReponseDTOList.get(0).category().toString()));
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("Should return a bookshelf")
    void getBookshelfByCategory_ShouldReturnbookshelf() throws Exception {
        when(bookshelfService.getBookshelfByCategory(any())).thenReturn(bookshelfReponseDTO);

        mockMvc.perform(get("/api/bookshelves/category")
                    .param("category", String.valueOf(Category.HORROR))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category").value(bookshelfReponseDTO.category().toString()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should create a new bookshelf")
    void createBookshelf_WithValidData_ReturnsCreted() throws Exception {
        String categoryJson = objectMapper.writeValueAsString(bookshelfRequestDTO);

        mockMvc.perform(post("/api/bookshelves")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(categoryJson)
                    .with(csrf()))
                .andExpect(status().isCreated());
    }
}