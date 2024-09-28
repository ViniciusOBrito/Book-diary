package com.brito.bookdiary.author;

import com.brito.bookdiary.author.dto.AuthorRequestDTO;
import com.brito.bookdiary.author.dto.AuthorRespondeDTO;
import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.exception.InvalidDataException;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorRespondeDTO> getAllAuthors(){
        return authorRepository.findAll()
                .stream()
                .map(AuthorRespondeDTO::new)
                .toList();
    }

    @Transactional
    public AuthorRespondeDTO registerAuthor(AuthorRequestDTO dto){

            if (authorRepository.findByEmail(dto.email()).isPresent()){
                throw new ResourceAlreadyExistException(String.format("Author with email %s already exist.", dto.email()));
            }

            Author author = new Author();
            author.setName(dto.name());
            author.setEmail(dto.email());
            author.setDateOfBirth(getDateFromString(dto.dateOfBirth()));
            author.setBooks(new ArrayList<>());

            author = saveAuthor(author);
            return new AuthorRespondeDTO(author);
    }

    public AuthorRespondeDTO updateAuthor(UUID authorId, AuthorRequestDTO dto){
        Author author =this.findOrThrow(authorId);

        author.setName(dto.name());
        author.setEmail(dto.email());
        author.setDateOfBirth(getDateFromString(dto.dateOfBirth()));

        author = authorRepository.save(author);

        return new AuthorRespondeDTO(author);
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


    public Date getDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new InvalidDataException(String.format("Error while trying to parse date %s. Please send a date in format dd/mm/yyyy", date));
        }
    }
}
