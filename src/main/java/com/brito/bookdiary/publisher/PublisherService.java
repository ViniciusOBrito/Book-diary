package com.brito.bookdiary.publisher;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.exception.ResourceAlreadyExistException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import com.brito.bookdiary.publisher.dto.PublisherRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

            if (publisherRepository.findByEmail(dto.email()).isPresent()){
                throw new ResourceAlreadyExistException(String.format("Publisher with email %s already exist.", dto.email()));
            }

            Publisher publisher = new Publisher();
            publisher.setName(dto.name());
            publisher.setEmail(dto.email());
            publisher.setBooks(new ArrayList<>());

            publisher = savePublisher(publisher);

            return new PublisherRespondeDTO(publisher);
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
