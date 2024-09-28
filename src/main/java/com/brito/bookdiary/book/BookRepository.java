package com.brito.bookdiary.book;

import com.brito.bookdiary.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    Optional<Book> findByTitleAndPublisher(String title, Publisher publisher);
}
