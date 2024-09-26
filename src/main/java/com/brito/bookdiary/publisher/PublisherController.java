package com.brito.bookdiary.publisher;

import com.brito.bookdiary.publisher.dto.PublisherRegisterRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/publisher")
@AllArgsConstructor
public class PublisherController implements PublisherResource {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherRespondeDTO>> getAllPublishers(){
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @PostMapping
    public ResponseEntity<PublisherRespondeDTO> registerPublisher(@RequestBody @Valid PublisherRegisterRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.registerPublisher(dto));
    }

}
