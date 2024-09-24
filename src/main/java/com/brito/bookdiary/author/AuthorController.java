package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRegisterRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import com.brito.bookdiary.book.Book;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorRespondeDTO>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<AuthorRespondeDTO> registerAuthor(@RequestBody @Valid AuthorRegisterRequestDTO dto){
        return ResponseEntity.ok(authorService.registerAuthor(dto));
    }

    @PutMapping("/link-books/{id}")
    public ResponseEntity<AuthorRespondeDTO> addBooksToAuthor(@PathVariable UUID authorID, @RequestBody List<Book> booksToLink){
        return ResponseEntity.ok(authorService.addBooksToAuthor(authorID, booksToLink));
    }


}
