package com.brito.bookdiary.post;

import com.brito.bookdiary.post.dto.PostDTO;
import com.brito.bookdiary.post.dto.PostRequestDTO;
import com.brito.bookdiary.post.dto.PostRespondeDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController implements PostResource{

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostRespondeDTO> createPost(@RequestBody @Valid PostRequestDTO dto, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(dto, request));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPostByUser(HttpServletRequest request){
        return ResponseEntity.ok(postService.getAllPostByUser(request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId){
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
