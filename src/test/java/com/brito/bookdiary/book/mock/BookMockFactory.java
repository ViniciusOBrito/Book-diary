package com.brito.bookdiary.book.mock;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.book.dto.BookRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import com.brito.bookdiary.bookshelf.Category;
import com.brito.bookdiary.publisher.Publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BookMockFactory {

    public static BookRespondeDTO mockBookResponseDTO(){
        return new BookRespondeDTO(
                UUID.randomUUID(),
                "Title",
                Category.HORROR,
                new Author(),
                new Publisher(),
                200L,
                new ArrayList<>()
        );
    }

    public static Book mockBook(){
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("Title");
        book.setCategory(Category.HORROR);
        book.setAuthor(new Author());
        book.setPublisher(new Publisher());
        book.setNumberOfPages(200L);
        book.setPosts(new ArrayList<>());

        return book;
    }

    public static List<Book> mockListBooks(){
        return Collections.nCopies(2, mockBook());
    }

    public static List<BookRespondeDTO> mockListBookDTO(){
        return Collections.nCopies(2, mockBookResponseDTO());
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
