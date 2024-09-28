package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Tag(name = "Authors Controller")
public interface AuthorResource {

    @Operation(summary = "Find all authors", description = "Retrive a list of all authors.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "List of all authors retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<AuthorRespondeDTO>> getAllAuthors();

    @Operation(summary = "Register a new author", description = "Register a new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<AuthorRespondeDTO> registerAuthor(@Valid AuthorRequestDTO dto);

    @Operation(summary = "Update a author", description = "Update a author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<AuthorRespondeDTO> updateAuthor(@PathVariable UUID authorId, @Valid AuthorRequestDTO dto);
}
