package com.brito.bookdiary.post;

import com.brito.bookdiary.post.dto.PostRequestDTO;
import com.brito.bookdiary.post.dto.PostRespondeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostRespondeDTO> createPost(@RequestBody @Valid PostRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(dto));
    }

    @GetMapping("/posts/{userEmail}")
    public ResponseEntity<List<PostRespondeDTO>> getAllPostByUser(@PathVariable String userEmail){
        return ResponseEntity.ok(postService.getAllPostByUser(userEmail));
    }
}
