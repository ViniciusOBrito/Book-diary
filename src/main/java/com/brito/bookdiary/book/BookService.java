package com.brito.bookdiary.book;

import com.brito.bookdiary.author.Author;
import com.brito.bookdiary.author.AuthorService;
import com.brito.bookdiary.book.dto.BookRegisterRequestDTO;
import com.brito.bookdiary.book.dto.BookRespondeDTO;
import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.publisher.Publisher;
import com.brito.bookdiary.publisher.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;


    @Transactional
    public BookRespondeDTO registerBook(BookRegisterRequestDTO dto){

        try{

            Author author = authorService.findOrThrow(dto.authorID());
            Publisher publisher = publisherService.findOrThrow(dto.publisherID());

            Book book = new Book();
            book.setTitle(dto.title());
            book.setCategory(dto.category());
            book.setAuthor(author);
            book.setPublisher(publisher);

            book = bookRepository.save(book);

            return new BookRespondeDTO(book);

        }catch (DataIntegrityViolationException exception){
            throw new RuntimeException("Book already exist.");
        }
    }

    public void addPostToBook(Post post){
        Book book = findOrThrow(post.getBook().getId());
        book.addPost(post);

        bookRepository.save(book);
    }


    public Book findOrThrow(UUID id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found."));
    }
}
