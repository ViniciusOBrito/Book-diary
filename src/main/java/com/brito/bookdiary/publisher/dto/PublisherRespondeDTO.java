package com.brito.bookdiary.publisher.dto;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.publisher.Publisher;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "Publisher Responde DTO", description = "DTO for responde containing infos about the publisher")
public record PublisherRespondeDTO(
        @Schema(description = "Id of the publisher", example = "1076ac4605a6440...")
        UUID id,
        @Schema(description = "Name of the publisher", example = "Grupo Editorial Record")
        String name,
        @Schema(description = "Email of the publisher", example = "comercial@record.com.br")
        String email,
        @Schema(description = "Book that are associated to that publisher")
        List<Book> books
) {

    public PublisherRespondeDTO(Publisher publisher){
        this(
                publisher.getId(),
                publisher.getName(),
                publisher.getEmail(),
                publisher.getBooks()
        );
    }
}
