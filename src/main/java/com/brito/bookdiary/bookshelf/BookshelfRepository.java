package com.brito.bookdiary.bookshelf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, UUID> {

    Optional<Bookshelf> findByCategory(String category);
}
