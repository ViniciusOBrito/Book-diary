package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRegisterRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
