package com.brito.bookdiary.bookshelf.mock;

import com.brito.bookdiary.bookshelf.Bookshelf;
import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
import jakarta.servlet.http.Cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class mockBookshelfFactory {

    public static BookshelfReponseDTO mockBookshelfResponseDTO(){
        return new BookshelfReponseDTO(mockBookshelf());
    }

    public static Bookshelf mockBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setId(UUID.randomUUID());
        bookshelf.setCategory(Category.HORROR);
        bookshelf.setBooks(new ArrayList<>());

        return bookshelf;
    }

    public static List<Bookshelf> mockListBookshelf(){
        return Collections.nCopies(2, mockBookshelf());
    }

    public static List<BookshelfReponseDTO> mockListBookshelfResponseDTO(){
        return Collections.nCopies(2, mockBookshelfResponseDTO());
    }

    public static BookshelfRequestDTO mockBookshelfRequestDTO(){
        return new BookshelfRequestDTO(
                Category.HORROR
        );
    }
}
