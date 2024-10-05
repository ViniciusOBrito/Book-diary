package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AuthorMockFactory {


    public static AuthorRespondeDTO mockAuthorDTO(){
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setName("Name example mock");
        author.setEmail("mock@example.com");
        author.setDateOfBirth(new Date());

        return new AuthorRespondeDTO(author);
    }

    public static AuthorRequestDTO mockRequestAuthorDTO(){
        return new AuthorRequestDTO(
                "Name example",
                "mock@example.com",
                "10/10/2022"
        );
    }

    public static List<AuthorRespondeDTO> mockListAuthorDTO(){
        List<AuthorRespondeDTO> mockListAuthorDto = new ArrayList<>();
        mockListAuthorDto.add(mockAuthorDTO());
        mockListAuthorDto.add(mockAuthorDTO());

        return mockListAuthorDto;
    }
}
