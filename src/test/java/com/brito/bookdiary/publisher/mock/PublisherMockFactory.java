package com.brito.bookdiary.publisher.mock;

import com.brito.bookdiary.publisher.dto.PublisherRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PublisherMockFactory {

    public static PublisherRespondeDTO mockPublisherResponseDTO(){
        return new PublisherRespondeDTO(
                UUID.randomUUID(),
                "Name example",
                "example@example.com",
                new ArrayList<>()
        );
    }

    public static List<PublisherRespondeDTO> mockListPublisherResponseDTO(){
        List<PublisherRespondeDTO> publisherRespondeDTOList = new ArrayList<>();
        publisherRespondeDTOList.add(mockPublisherResponseDTO());
        publisherRespondeDTOList.add(mockPublisherResponseDTO());

        return publisherRespondeDTOList;
    }

    public static PublisherRequestDTO mockPublisherRequestDTO(){
        return new PublisherRequestDTO(
                "Name example",
                "example@example.com"
        );
    }
}
