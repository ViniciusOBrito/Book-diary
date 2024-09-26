package com.brito.bookdiary.book;

import com.brito.bookdiary.book.dto.BookRegisterRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Books Controller")
public interface BookResource {

    @Operation(summary = "Find all books", description = "Retrive a list of all books.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "List of all books retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<BookRespondeDTO>> getAllBooks();


    @Operation(summary = "Register a new book", description = "Register a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<BookRespondeDTO> registerBook(@Valid BookRegisterRequestDTO dto);
}
