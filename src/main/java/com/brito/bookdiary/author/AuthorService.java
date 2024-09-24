package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRegisterRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    public List<AuthorRespondeDTO> getAllAuthors(){
        return authorRepository.findAll()
                .stream()
                .map(AuthorRespondeDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorRespondeDTO registerAuthor(AuthorRegisterRequestDTO dto){
        validateAuthorAlreadyExist(dto.email());

        Author author = new Author();
        author.setName(dto.name());
        author.setEmail(dto.email());
        author.setDateOfBirth(getDateFromString(dto.dateOfBirth()));

        author = authorRepository.save(author);
        return new AuthorRespondeDTO(author);
    }

    @Transactional
    public AuthorRespondeDTO addBooksToAuthor(UUID authorID , List<Book> booksToLink){

        Author author = findOrThrow(authorID);

        List<Book> linkedBooks = booksToLink.stream()
                        .map(book -> bookService.findOrThrow(book.getId()))
                        .collect(Collectors.toList());

        author.setBooks(linkedBooks);
        author = authorRepository.save(author);

        return new AuthorRespondeDTO(author);
    }

    public Author findOrThrow(UUID authorId){
        return authorRepository.findById(authorId)
                .orElseThrow(()-> new RuntimeException("Author not found"));
    }

    public void validateAuthorAlreadyExist(String email){
        authorRepository.findByEmail(email)
                .ifPresent(author -> new RuntimeException("Author with email " + email + " already register"));
    }

    public Date getDateFromString(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
