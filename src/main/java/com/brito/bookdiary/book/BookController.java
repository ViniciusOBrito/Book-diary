package com.brito.bookdiary.book;

import com.brito.bookdiary.book.dto.BookRegisterRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookRespondeDTO> registerBook(@RequestBody @Valid BookRegisterRequestDTO dto){
        return ResponseEntity.ok(bookService.registerBook(dto));
    }
}
