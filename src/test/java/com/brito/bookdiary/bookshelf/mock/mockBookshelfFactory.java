package com.brito.bookdiary.bookshelf.mock;

import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
import jakarta.servlet.http.Cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mockBookshelfFactory {

    public static BookshelfReponseDTO mockBookshelfResponseDTO(){
        return new BookshelfReponseDTO(
                Category.HORROR,
                new ArrayList<>()
        );
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
