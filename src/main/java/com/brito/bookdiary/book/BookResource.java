package com.brito.bookdiary.book;

import com.brito.bookdiary.book.dto.BookRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Tag(name = "Books Controller")
public interface BookResource {

    @Operation(summary = "Find all books", description = "Retrieve a list of all books.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "List of all books retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<BookRespondeDTO>> getAllBooks();

    @Operation(summary = "Find a book", description = "Retrieve a book.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a book successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<BookRespondeDTO> getBook(UUID bookId);

    @Operation(summary = "Find all books associated to a user", description = "Retrieve list of all books associated to a user.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "List of all books associated to a user retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<BookRespondeDTO>> getBooksByUser(HttpServletRequest request);


    @Operation(summary = "Register a new book", description = "Register a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<BookRespondeDTO> registerBook(@Valid BookRequestDTO dto);

    @Operation(summary = "Update a book", description = "Update infos about the book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<BookRespondeDTO> updateBook(@PathVariable UUID bookId, @Valid BookRequestDTO dto);

    @Operation(summary = "Delete a book", description = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Void> deleteBook(@PathVariable UUID bookId);
}
