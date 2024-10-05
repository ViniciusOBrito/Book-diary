package com.brito.bookdiary.book;


import com.brito.bookdiary.auth.AuthService;
import com.brito.bookdiary.book.dto.BookRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
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

import static com.brito.bookdiary.book.mock.BookMockFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TokenService tokenService;
    @MockBean
    AuthService authService;
    @MockBean
    BookService bookService;
    @MockBean
    UserService userService;

    private BookRespondeDTO bookRespondeDTO;
    private BookRequestDTO bookRequestDTO;
    private List<BookRespondeDTO> bookRespondeDTOList;

    @BeforeEach
    void setUp(){
        bookRespondeDTO = mockBookResponseDTO();
        bookRequestDTO = mockBookRequestDTO();
        bookRespondeDTOList = mockListBookDTO();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("Should return a list of all books")
    void getAllBooks_ShouldReturnBooksList() throws Exception {
        when(bookService.getAllBooks()).thenReturn(bookRespondeDTOList);

        mockMvc.perform(get("/api/books")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(bookRespondeDTOList.get(0).id().toString()))
                .andExpect(jsonPath("$[0].name").value(bookRespondeDTOList.get(0).name()));
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("Should return a book")
    void getBookById_ShouldReturnBook() throws Exception {
        when(bookService.getBook(any())).thenReturn(bookRespondeDTO);

        mockMvc.perform(get("/api/books/book")
                    .param( "bookId",String.valueOf(UUID.randomUUID()))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookRespondeDTO.id().toString()))
                .andExpect(jsonPath("$.name").value(bookRespondeDTO.name()));
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("Should return a list of books associated to a user")
    void getAllBooksByUser_ShouldReturnBooksAssociatedToUser() throws Exception {
        when(bookService.getBooksByUser(any())).thenReturn(bookRespondeDTOList);

        mockMvc.perform(get("/api/books/user")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(bookRespondeDTOList.get(0).id().toString()))
                .andExpect(jsonPath("$[0].name").value(bookRespondeDTOList.get(0).name()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should create a new book and return Created")
    void createBook_WithValidData_ReturnsCreated() throws Exception{
        String bookJson = objectMapper.writeValueAsString(bookRequestDTO);

        mockMvc.perform(post("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookJson)
                    .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should update a book and return Ok")
    void updateBook_WithValidData_ReturnOk() throws Exception{
        String bookJson = objectMapper.writeValueAsString(bookRequestDTO);

        mockMvc.perform(put("/api/books/{bookId}", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookJson)
                    .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should delete a book by id and return No Content")
    void deleteBookById_ReturnNoContent() throws Exception {
        when(bookService.findOrThrow(any())).thenReturn(new Book());

        mockMvc.perform(delete("/api/books/{bookId}", UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isNoContent());
    }
}