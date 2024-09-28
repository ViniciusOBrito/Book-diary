package com.brito.bookdiary.bookshelf;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.bookshelf.dto.BookshelfReponseDTO;
import com.brito.bookdiary.bookshelf.dto.BookshelfRequestDTO;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookshelfService {

    private final BookshelfRepository bookshelfRepository;

    @Transactional
    public BookshelfReponseDTO createBookshelf(BookshelfRequestDTO dto){

            if (bookshelfRepository.findByCategory(dto.category()).isPresent()){
                throw new ResourceAlreadyExistException(String.format("Bookshelf with the category %s already exist.", dto.category().getName()));
            }

            Bookshelf bookshelf = new Bookshelf();
            bookshelf.setCategory(dto.category());
            bookshelf.setBooks(new ArrayList<>());

            bookshelf = bookshelfRepository.save(bookshelf);

            return new BookshelfReponseDTO(bookshelf);
    }

    public void addBookToBookShelf(Book book){
        Bookshelf bookshelf = findByCategoryOrThrow(book.getCategory());
        List<Book> books = bookshelf.getBooks();

        books.add(book);
        bookshelfRepository.save(bookshelf);
    }

    public List<BookshelfReponseDTO> getAllBookShelf(){
        return bookshelfRepository.findAll()
                .stream()
                .map(BookshelfReponseDTO::new)
                .collect(Collectors.toList());
    }

    public BookshelfReponseDTO getBookshelfByCategory(Category category){
        return new BookshelfReponseDTO(this.findByCategoryOrThrow(category));
    }

    public Bookshelf findByCategoryOrThrow(Category category){
        return bookshelfRepository.findByCategory(category)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Bookshelf with category %s not found. You can create one.", category.getName())));
    }
}
