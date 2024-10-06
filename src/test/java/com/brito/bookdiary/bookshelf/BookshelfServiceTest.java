package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
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

import static com.brito.bookdiary.bookshelf.mock.mockBookshelfFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookshelfServiceTest {

    @Mock
    BookshelfRepository bookshelfRepository;
    @InjectMocks
    BookshelfService bookshelfService;

    @Test
    @DisplayName("Should create a new bookshelf when successful")
    void createBookshelf_WithValidData_WhenSuccessful(){
        when(bookshelfRepository.findByCategory(any())).thenReturn(Optional.empty());
        when(bookshelfRepository.save(any(Bookshelf.class))).thenReturn(mockBookshelf());

        BookshelfReponseDTO result = bookshelfService.createBookshelf(mockBookshelfRequestDTO());

        assertNotNull(result);
        assertEquals(mockBookshelfResponseDTO().category(), result.category());
    }

    @Test
    @DisplayName("Should return a list of all bookshelves")
    void getAllBooks_ShouldReturnsBookshelfList(){
        when(bookshelfRepository.findAll()).thenReturn(mockListBookshelf());

        List<BookshelfReponseDTO> result = bookshelfService.getAllBookShelf();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should return a bookshelf by the category")
    void getBookshelf_ReturnBookshelf_WhenSuccessful(){
        when(bookshelfRepository.findByCategory(any())).thenReturn(Optional.of(mockBookshelf()));

        BookshelfReponseDTO result = bookshelfService.getBookshelfByCategory(Category.HORROR);

        assertNotNull(result);
        assertEquals(mockBookshelfResponseDTO().category(), result.category());
    }

    @Test
    @DisplayName("Should throw ResourceAlreadyExist when bookshelf with category already exist")
    void createBookshelf_ReturnsResourceAlreadyExist_WhenBookshelfWithCategoryAlreadyExist(){
        when(bookshelfRepository.findByCategory(any())).thenReturn(Optional.of(mockBookshelf()));

        assertThrows(ResourceAlreadyExistException.class, () -> {
           bookshelfService.createBookshelf(mockBookshelfRequestDTO());
        });
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when bookshelf with category not found")
    void getBookshelf_ReturnsResourceNotFound_WhenBookshelfNotFound(){
        when(bookshelfRepository.findByCategory(any())).thenThrow(new ResourceNotFoundException("Bookshelf not found"));

        assertThrows(ResourceNotFoundException.class, () -> {
            bookshelfService.getBookshelfByCategory(Category.HORROR);
        });
    }

}