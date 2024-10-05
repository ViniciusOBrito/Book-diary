package com.brito.bookdiary.author.mock;

import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AuthorMockFactory {


    public static AuthorRespondeDTO mockAuthorDTO(){
        return new AuthorRespondeDTO(
                UUID.randomUUID(),
                "Name example",
                new Date(),
                new ArrayList<>()

        );
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
