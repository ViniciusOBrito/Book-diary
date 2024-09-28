package com.brito.bookdiary.book;

import com.brito.bookdiary.book.dto.BookRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController implements BookResource{

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookRespondeDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/book")
    public ResponseEntity<BookRespondeDTO> getBook(@RequestParam UUID bookId){
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<BookRespondeDTO>> getBooksByUser(@RequestParam String userEmail){
        return ResponseEntity.ok(bookService.getBooksByUser(userEmail));
    }

    @PostMapping
    public ResponseEntity<BookRespondeDTO> registerBook(@RequestBody @Valid BookRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.registerBook(dto));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookRespondeDTO> updateBook(@PathVariable UUID bookId, @RequestBody @Valid BookRequestDTO dto){
        return ResponseEntity.ok(bookService.updateBook(bookId,dto));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}
