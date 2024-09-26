package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRegisterRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<AuthorRespondeDTO> getAllAuthors(){
        return authorRepository.findAll()
                .stream()
                .map(AuthorRespondeDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorRespondeDTO registerAuthor(AuthorRegisterRequestDTO dto){

        try {
            Author author = new Author();
            author.setName(dto.name());
            author.setEmail(dto.email());
            author.setDateOfBirth(getDateFromString(dto.dateOfBirth()));

            author = saveAuthor(author);
            return new AuthorRespondeDTO(author);

        }catch (DataIntegrityViolationException e){
            throw new ResourceAlreadyExistException(String.format("Author with email %s already exist.", dto.email()));
        }
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void addBooksToAuthor(Author author , Book bookToLink){
        List<Book> books = author.getBooks();
        books.add(bookToLink);
        this.saveAuthor(author);
    }


    public Author findOrThrow(UUID authorId){
        return authorRepository.findById(authorId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Author with id %s not found", authorId)));
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
