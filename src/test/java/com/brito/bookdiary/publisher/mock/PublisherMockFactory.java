package com.brito.bookdiary.publisher.mock;

import com.brito.bookdiary.publisher.Publisher;
import com.brito.bookdiary.publisher.dto.PublisherRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PublisherMockFactory {

    public static PublisherRespondeDTO mockPublisherResponseDTO(){
        return new PublisherRespondeDTO(mockPublisher());
    }

    public static Publisher mockPublisher(){
        Publisher publisher = new Publisher();
        publisher.setId(UUID.randomUUID());
        publisher.setEmail("example@example.com");
        publisher.setName("Name example");
        publisher.setBooks(new ArrayList<>());

        return publisher;
    }

    public static List<Publisher> mockListPublisher(){
        return Collections.nCopies(2, mockPublisher());
    }

    public static List<PublisherRespondeDTO> mockListPublisherResponseDTO(){
        return Collections.nCopies(2, mockPublisherResponseDTO());
    }

    public static PublisherRequestDTO mockPublisherRequestDTO(){
        return new PublisherRequestDTO(
                "Name example",
                "example@example.com"
        );
    }
}
