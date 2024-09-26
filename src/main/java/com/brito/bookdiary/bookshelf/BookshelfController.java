package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bookshelf")
@AllArgsConstructor
public class BookshelfController implements BookshelfResource{

    private final BookshelfService bookshelfService;


    @GetMapping
    public ResponseEntity<List<BookshelfReponseDTO>> getAllBookshelf(){
        return ResponseEntity.ok(bookshelfService.getAllBookShelf());
    }

    @GetMapping("/category")
    public ResponseEntity<BookshelfReponseDTO> getBookshelfByCategory(@RequestParam Category category){
        return ResponseEntity.ok(bookshelfService.getBookshelfByCategory(category));
    }

    @PostMapping
    public ResponseEntity<BookshelfReponseDTO> createBookshelf(@RequestBody @Valid BookshelfRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookshelfService.createBookshelf(dto));
    }

}
