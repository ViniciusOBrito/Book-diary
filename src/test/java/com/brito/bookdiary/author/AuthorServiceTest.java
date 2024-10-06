package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.brito.bookdiary.author.mock.AuthorMockFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class AuthorServiceTest {


    @Mock
    AuthorRepository authorRepository;
    @InjectMocks
    AuthorService authorService;


    @Test
    @DisplayName("Should return a list of authors")
    void getAllAuthors_ReturnAllAuthors_WhenSuccessful(){
        when(authorRepository.findAll()).thenReturn(mockListAuthors());

        List<AuthorRespondeDTO> result = authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    @DisplayName("Should register a new author when successful")
    void registerAuthor_WithValidData_WhenSuccessful(){
        Author author = mockAuthor();
        when(authorRepository.findByEmail(mockAuthor().getEmail())).thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorRespondeDTO result = authorService.registerAuthor(mockRequestAuthorDTO());

        assertNotNull(result);
        assertEquals(mockAuthor().getName(), result.name());
    }

    @Test
    @DisplayName("Should update a author when successful")
    void updateAuthor_WithValidData_WhenSuccessful(){
        when(authorRepository.findById(any())).thenReturn(Optional.of(mockAuthor()));
        when(authorRepository.save(any(Author.class))).thenReturn(mockAuthor());

        AuthorRespondeDTO result = authorService.updateAuthor(UUID.randomUUID(), mockRequestAuthorDTO());

        assertNotNull(result);
        assertEquals(mockAuthorDTO().name(), result.name());
    }

    @Test
    @DisplayName("Should throw ResourceAlreadyExist when author email already registered")
    void registerAuthor_ReturnsResourceAlreadyExist_WhenEmailAuthorAlreadyRegistered(){
        when(authorRepository.findByEmail(mockRequestAuthorDTO().email())).thenReturn(Optional.of(mockAuthor()));

        assertThrows(ResourceAlreadyExistException.class, () -> {
            authorService.registerAuthor(mockRequestAuthorDTO());
        });
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when author email non-registered")
    void updateAuthor_ReturnsResourceNotFound_WhenEmailAuthorNotFound(){
        when(authorRepository.findById(UUID.randomUUID())).thenThrow(new ResourceAlreadyExistException("Author not found"));

        assertThrows(ResourceNotFoundException.class, () ->{
           authorService.updateAuthor(UUID.randomUUID(), mockRequestAuthorDTO());
        });
    }
}