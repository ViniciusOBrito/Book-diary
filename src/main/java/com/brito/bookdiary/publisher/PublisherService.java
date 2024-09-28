package com.brito.bookdiary.publisher;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.publisher.dto.PublisherRequestDTO;
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

    public List<PublisherRespondeDTO> getAllPublishers(){
        return publisherRepository.findAll()
                .stream()
                .map(PublisherRespondeDTO::new)
                .toList();
    }

    @Transactional
    public PublisherRespondeDTO registerPublisher(PublisherRequestDTO dto){

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

    @Transactional
    public PublisherRespondeDTO updatePublisher(UUID publisherId, PublisherRequestDTO dto){

        Publisher publisher = this.findOrThrow(publisherId);

        publisher.setName(dto.name());
        publisher.setEmail(dto.email());

        publisher = publisherRepository.save(publisher);

        return new PublisherRespondeDTO(publisher);
    }

    public Publisher savePublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }

    public void addBooksToPublisher(Publisher publisher, Book bookToLink){
        List<Book> books = publisher.getBooks();
        books.add(bookToLink);
        this.savePublisher(publisher);
    }

    public Publisher findOrThrow(UUID publisherId){
        return publisherRepository.findById(publisherId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Publisher with id %s not found.", publisherId) ));
    }

}
