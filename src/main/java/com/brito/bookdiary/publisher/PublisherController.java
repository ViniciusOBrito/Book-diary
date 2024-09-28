package com.brito.bookdiary.publisher;

import com.brito.bookdiary.publisher.dto.PublisherRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/publishers")
@AllArgsConstructor
public class PublisherController implements PublisherResource {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherRespondeDTO>> getAllPublishers(){
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @PostMapping
    public ResponseEntity<PublisherRespondeDTO> registerPublisher(@RequestBody @Valid PublisherRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.registerPublisher(dto));
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<PublisherRespondeDTO> updatePublisher(@PathVariable UUID publisherId, @RequestBody @Valid PublisherRequestDTO dto){
        return ResponseEntity.ok(publisherService.updatePublisher(publisherId, dto));
    }

}
