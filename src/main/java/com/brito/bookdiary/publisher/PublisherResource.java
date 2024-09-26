package com.brito.bookdiary.publisher;

import com.brito.bookdiary.publisher.dto.PublisherRegisterRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Publishers Controller")
public interface PublisherResource {

    @Operation(summary = "Find all publishers", description = "Retrive a list of all publishers")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "List of all publishers retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<PublisherRespondeDTO>> getAllPublishers();


    @Operation(summary = "Create a new publisher", description = "Create a new publisher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publisher registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<PublisherRespondeDTO> registerPublisher(@Valid PublisherRegisterRequestDTO dto);
}
