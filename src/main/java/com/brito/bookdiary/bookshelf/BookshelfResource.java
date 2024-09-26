package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Bookshelfs Controller")
public interface BookshelfResource {

    @Operation(summary = "Find all bookshelfs", description = "Retrive a list of all bookshelfs")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "List of all bookshelfs retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<BookshelfReponseDTO>> getAllBookshelf();

    @Operation(summary = "Find a bookshelf by the category", description = "Retrive a bookshelf by the category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookshelf retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Bookshelf not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<BookshelfReponseDTO> getBookshelfByCategory(Category category);

    @Operation(summary = "Create a new bookshelf", description = "Create a new bookshelf of a category that not already exist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bookshelf registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<BookshelfReponseDTO> createBookshelf(@Valid BookshelfRequestDTO dto);
}
