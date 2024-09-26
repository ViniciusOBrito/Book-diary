package com.brito.bookdiary.publisher;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.publisher.dto.PublisherRegisterRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Transactional
    public PublisherRespondeDTO registerPublisher(PublisherRegisterRequestDTO dto){

        try {
            Publisher publisher = new Publisher();
            publisher.setName(dto.name());
            publisher.setEmail(dto.email());

            publisher = savePublisher(publisher);

            return new PublisherRespondeDTO(publisher);

        }catch (DataIntegrityViolationException e){
            throw new ResourceAlreadyExistException(String.format("Publisher with email %s already exist.", dto.email()));
        }

    }

    public Publisher savePublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }

    public void addBooksToPublisher(Publisher publisher, Book bookToLink){
        List<Book> books = publisher.getBooks();
        books.add(bookToLink);
        this.savePublisher(publisher);
    }

    public List<PublisherRespondeDTO> getAllPublishers(){
        return publisherRepository.findAll()
                .stream()
                .map(PublisherRespondeDTO::new)
                .collect(Collectors.toList());
    }

    public Publisher findOrThrow(UUID publisherId){
        return publisherRepository.findById(publisherId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Publisher with id %s not found.", publisherId) ));
    }

}
