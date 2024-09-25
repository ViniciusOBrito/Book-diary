package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.book.BookService;
import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookshelfService {

    private final BookService bookService;
    private final BookshelfRepository bookshelfRepository;

    @Transactional
    public BookshelfReponseDTO createBookshelf(BookshelfRequestDTO dto){

        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setCategory(dto.category());

        bookshelf = bookshelfRepository.save(bookshelf);

        return new BookshelfReponseDTO(bookshelf);

    }

    public BookshelfReponseDTO addBooksToBookshelf(BookshelfRequestDTO dto){
        Bookshelf bookshelf = findBookshelfByCategory(dto.category());
        String category = bookshelf.getCategory();

        validateBooksAddToBookshelf(category, dto.books());
        dto.books().forEach(book -> bookService.findOrThrow(book.getId()));

        bookshelf.setBooks(dto.books());

        bookshelf = bookshelfRepository.save(bookshelf);

        return new BookshelfReponseDTO(bookshelf);
    }



    public void validateBooksAddToBookshelf(String category, List<Book> books){
        boolean hasDifferentCategory =  books.stream()
                .anyMatch(book -> !book.getCategory().equals(category));

        if (hasDifferentCategory){
            throw new RuntimeException("one or more books have a category different of the bookshelf category");
        }
    }

    public List<BookshelfReponseDTO> getAllBookShelf(){
        return bookshelfRepository.findAll()
                .stream()
                .map(BookshelfReponseDTO::new)
                .collect(Collectors.toList());
    }

    public BookshelfReponseDTO getBookshelfByCategory(String category){
        return new BookshelfReponseDTO(this.findBookshelfByCategory(category));
    }

    public Bookshelf findBookshelfByCategory(String category){
        return bookshelfRepository.findByCategory(category)
                .orElseThrow(()-> new RuntimeException("Bookshelf with category " + category + " not found. You can create one."));
    }
}
