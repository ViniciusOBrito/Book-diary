package com.brito.bookdiary.book;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.author.AuthorService;
import com.brito.bookdiary.book.dto.BookRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import com.brito.bookdiary.bookshelf.BookshelfService;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;
import com.brito.bookdiary.publisher.PublisherService;
import com.brito.bookdiary.user.User;
import com.brito.bookdiary.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private BookshelfService bookshelfService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final UserService userService;



    public List<BookRespondeDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(BookRespondeDTO::new)
                .toList();
    }

    public BookRespondeDTO getBook(UUID bookId){
        Book book = this.findOrThrow(bookId);

        return new BookRespondeDTO(book);
    }

    public List<BookRespondeDTO> getBooksByUser(String userEmail){
        User user = userService.findOrThrow(userEmail);

        return bookRepository.findAll(BookSpecification.booksByUser(user.getId()))
                .stream()
                .map(BookRespondeDTO::new)
                .toList();
    }

    @Transactional
    public BookRespondeDTO registerBook(BookRequestDTO dto){

            Author author = authorService.findOrThrow(dto.authorID());
            Publisher publisher = publisherService.findOrThrow(dto.publisherID());

        if((bookRepository.findByTitleAndPublisher(dto.title(), publisher).isPresent())){
            throw new ResourceAlreadyExistException(String.format("Book with title %s and publisher %s already exist", dto.title(), publisher.getName()));
        }

            Book book = new Book();
            book.setTitle(dto.title());
            book.setCategory(dto.category());
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setNumberOfPages(dto.numberOfPages());

            book = bookRepository.save(book);

            addBookToBookshelf(book);
            addBooksToAuthor(author, book);
            addBooksToPublisher(publisher, book);

            return new BookRespondeDTO(book);

    }

    @Transactional
    public BookRespondeDTO updateBook(UUID bookId, BookRequestDTO dto){

        Book book = this.findOrThrow(bookId);
        Author author = authorService.findOrThrow(dto.authorID());
        Publisher publisher = publisherService.findOrThrow(dto.publisherID());

        book.setTitle(dto.title());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategory(dto.category());
        book.setNumberOfPages(dto.numberOfPages());

        book = bookRepository.save(book);

        return new BookRespondeDTO(book);
    }

    @Transactional
    public void deleteBook(UUID bookId){
        Book book = this.findOrThrow(bookId);
        bookRepository.delete(book);
    }



    public void addPostToBook(Post post){
        Book book = findOrThrow(post.getBook().getId());
        book.addPost(post);

        bookRepository.save(book);
    }

    public void addBooksToAuthor(Author author , Book bookToLink){
        authorService.addBooksToAuthor(author, bookToLink);
    }

    public void addBooksToPublisher(Publisher publisher, Book bookToLink){
        publisherService.addBooksToPublisher(publisher, bookToLink);
    }

    public void addBookToBookshelf(Book book){
        bookshelfService.addBookToBookShelf(book);
    }

    public Book findOrThrow(UUID bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Book with id %s not found.", bookId)));
    }
}
