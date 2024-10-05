package com.brito.bookdiary.book;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.dto.BookRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.publisher.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookMockFactory {

    public static BookRespondeDTO mockBookResponseDTO(){
        return new BookRespondeDTO(
                UUID.randomUUID(),
                "Book title example",
                Category.HORROR,
                new Author(),
                new Publisher(),
                10L,
                new ArrayList<>()
        );
    }

    public static List<BookRespondeDTO> mockListBookDTO(){
        List<BookRespondeDTO> mockListBookDTO = new ArrayList<>();
        mockListBookDTO.add(mockBookResponseDTO());
        mockListBookDTO.add(mockBookResponseDTO());

        return mockListBookDTO;
    }

    public static BookRequestDTO mockBookRequestDTO(){
        return new BookRequestDTO(
                "Book title example",
                Category.HORROR,
                UUID.randomUUID(),
                UUID.randomUUID(),
                130L
        );
    }
}
