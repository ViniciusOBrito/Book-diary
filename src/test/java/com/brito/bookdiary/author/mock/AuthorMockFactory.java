package com.brito.bookdiary.author.mock;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;

import java.util.*;

public class AuthorMockFactory {


    public static AuthorRespondeDTO mockAuthorDTO(){
        return new AuthorRespondeDTO(mockAuthor());
    }

    public static Author mockAuthor(){
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setEmail("example@example.com");
        author.setName("Name");
        author.setDateOfBirth(new Date());
        author.setBooks(new ArrayList<>());

        return author;
    }

    public static List<Author> mockListAuthors(){
        return Collections.nCopies(2, mockAuthor());
    }

    public static List<AuthorRespondeDTO> mockListAuthorDTO(){
        return Collections.nCopies(2, mockAuthorDTO());
    }

    public static AuthorRequestDTO mockRequestAuthorDTO(){
        return new AuthorRequestDTO(
                "Name example",
                "mock@example.com",
                "10/10/2022"
        );
    }
}
