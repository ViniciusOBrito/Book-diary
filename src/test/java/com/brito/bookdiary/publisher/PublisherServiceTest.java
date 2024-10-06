package com.brito.bookdiary.publisher;

import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.brito.bookdiary.publisher.mock.PublisherMockFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PublisherServiceTest {

    @Mock
    PublisherRepository publisherRepository;
    @InjectMocks
    PublisherService publisherService;


    @Test
    @DisplayName("Should return a list of Publishers")
    void getAllPublishers_ReturnAllPublishers_WhenSuccessful(){
        when(publisherRepository.findAll()).thenReturn(mockListPublisher());

        List<PublisherRespondeDTO> result = publisherService.getAllPublishers();

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    @DisplayName("Should register a new Publisher when successful")
    void registerPublisher_WithValidData_WhenSuccessful(){
        when(publisherRepository.findByEmail(mockPublisher().getEmail())).thenReturn(Optional.empty());
        when(publisherRepository.save(any(Publisher.class))).thenReturn(mockPublisher());

        PublisherRespondeDTO result = publisherService.registerPublisher(mockPublisherRequestDTO());

        assertNotNull(result);
        assertEquals(mockPublisherResponseDTO().name(), result.name());
    }

    @Test
    @DisplayName("Should update a Publisher when successful")
    void updatePublisher_WithValidData_WhenSuccessful(){
        when(publisherRepository.findById(any())).thenReturn(Optional.of(mockPublisher()));
        when(publisherRepository.save(any(Publisher.class))).thenReturn(mockPublisher());

        PublisherRespondeDTO result = publisherService.updatePublisher(UUID.randomUUID(), mockPublisherRequestDTO());

        assertNotNull(result);
        assertEquals(mockPublisherResponseDTO().name(), result.name());
    }

    @Test
    @DisplayName("Should throw ResourceAlreadyExist when Publisher email already registered")
    void registerPublisher_ReturnsResourceAlreadyExist_WhenEmailPublisherAlreadyRegistered(){
        when(publisherRepository.findByEmail(mockPublisherRequestDTO().email())).thenReturn(Optional.of(mockPublisher()));

        assertThrows(ResourceAlreadyExistException.class, () -> {
            publisherService.registerPublisher(mockPublisherRequestDTO());
        });
    }

    @Test
    @DisplayName("Should throw ResourceNotFound when Publisher email non-registered")
    void updatePublisher_ReturnsResourceNotFound_WhenEmailPublisherNotFound(){
        when(publisherRepository.findById(UUID.randomUUID())).thenThrow(new ResourceAlreadyExistException("Publisher not found"));

        assertThrows(ResourceNotFoundException.class, () ->{
            publisherService.updatePublisher(UUID.randomUUID(), mockPublisherRequestDTO());
        });
    }

}