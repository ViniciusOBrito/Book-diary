package com.brito.bookdiary.book;

import com.brito.bookdiary.book.dto.BookRegisterRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController implements BookResource{

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookRespondeDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookRespondeDTO> registerBook(@RequestBody @Valid BookRegisterRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.registerBook(dto));
    }
}
