package com.brito.bookdiary.book;

import com.brito.bookdiary.author.AuthorService;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import com.brito.bookdiary.bookshelf.BookshelfService;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.publisher.Publisher;
import com.brito.bookdiary.publisher.PublisherService;
import com.brito.bookdiary.security.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.brito.bookdiary.author.mock.AuthorMockFactory.mockAuthor;
import static com.brito.bookdiary.author.mock.AuthorMockFactory.mockRequestAuthorDTO;
import static com.brito.bookdiary.book.mock.BookMockFactory.*;
import static com.brito.bookdiary.publisher.mock.PublisherMockFactory.mockPublisher;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    BookshelfService bookshelfService;
    @Mock
    AuthorService authorService;
    @Mock
    PublisherService publisherService;
    @Mock
    TokenService tokenService;
    @InjectMocks
    BookService bookService;


    @Test
    @DisplayName("Should return a list off all books")
    void getAllBooks_ShouldReturnBookList(){
        when(bookRepository.findAll()).thenReturn(mockListBooks());

        List<BookRespondeDTO> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should return a book by the id")
    void getBook_ReturnBook_WhenSuccessful(){
        when(bookRepository.findById(any())).thenReturn(Optional.of(mockBook()));

        BookRespondeDTO result = bookService.getBook(UUID.randomUUID());

        assertNotNull(result);
        assertEquals(mockBookResponseDTO().name(), result.name());
        assertEquals(mockBookResponseDTO().category(), result.category());
        assertEquals(mockBookResponseDTO().numberOfPages(), result.numberOfPages());
    }

    @Test
    @DisplayName("Should register a new book when successful")
    void registerBook_WithValidData_WhenSuccessful(){
        when(authorService.findOrThrow(any())).thenReturn(mockAuthor());
        when(publisherService.findOrThrow(any())).thenReturn(mockPublisher());

        when(bookRepository.findByTitleAndPublisher(anyString(), any(Publisher.class))).thenReturn(Optional.empty());

        when(bookRepository.save(any(Book.class))).thenReturn(mockBook());

        BookRespondeDTO result = bookService.registerBook(mockBookRequestDTO());

        assertNotNull(result);
        assertEquals(mockBookResponseDTO().name(), result.name());
        assertEquals(mockBookResponseDTO().numberOfPages(), result.numberOfPages());
    }

    @Test
    @DisplayName("Should update a book when successful")
    void updateBook_WithValidData_WhenSuccessful(){
        when(bookRepository.findById(any())).thenReturn(Optional.of(mockBook()));
        when(authorService.findOrThrow(any())).thenReturn(mockAuthor());
        when(publisherService.findOrThrow(any())).thenReturn(mockPublisher());

        when(bookRepository.save(any(Book.class))).thenReturn(mockBook());

        BookRespondeDTO result = bookService.updateBook(UUID.randomUUID(), mockBookRequestDTO());

        assertNotNull(result);
        assertEquals(mockBookResponseDTO().name(), result.name());
        assertEquals(mockBookResponseDTO().numberOfPages(), result.numberOfPages());
    }

    @Test
    @DisplayName("Should throw a ResourceAlreadyExist when a book with title and publisher already exist")
    void registerBook_ReturnsResourceAlreadyExist_WhenBookAlreadyRegistered(){
        when(authorService.findOrThrow(any())).thenReturn(mockAuthor());
        when(publisherService.findOrThrow(any())).thenReturn(mockPublisher());
        when(bookRepository.findByTitleAndPublisher(anyString(), any(Publisher.class))).thenReturn(Optional.of(mockBook()));

        assertThrows(ResourceAlreadyExistException.class, () -> {
            bookService.registerBook(mockBookRequestDTO());
        });
    }

    @Test
    @DisplayName("Should throw a ResourceNotFound when book id not found")
    void updateBook_ReturnsResourceNotFound_WhenBookIdNotFound(){
        when(bookRepository.findById(any())).thenThrow(new ResourceNotFoundException("Book not found."));

        assertThrows(ResourceNotFoundException.class, () -> {
           bookService.updateBook(UUID.randomUUID(), mockBookRequestDTO());
        });
    }

}