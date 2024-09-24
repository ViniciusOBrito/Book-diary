package com.brito.bookdiary.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    Optional<Publisher> findByEmail(String email);
}
