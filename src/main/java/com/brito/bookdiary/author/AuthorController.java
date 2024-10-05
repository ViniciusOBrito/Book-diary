package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/authors")
@AllArgsConstructor
public class AuthorController implements AuthorResource{

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorRespondeDTO>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<AuthorRespondeDTO> registerAuthor(@RequestBody @Valid AuthorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.registerAuthor(dto));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorRespondeDTO> updateAuthor(@PathVariable UUID authorId, @RequestBody @Valid AuthorRequestDTO dto){
        return ResponseEntity.ok(authorService.updateAuthor(authorId, dto));
    }

}
