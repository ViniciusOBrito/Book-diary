package com.brito.bookdiary.post;

import com.brito.bookdiary.post.dto.PostDTO;
import com.brito.bookdiary.post.dto.PostRequestDTO;
import com.brito.bookdiary.post.dto.PostRespondeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Posts Controller")
public interface PostResource {

    @Operation(summary = "Create a new post", description = "Create a new post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<PostRespondeDTO> createPost(@Valid PostRequestDTO dto, HttpServletRequest request);

    @Operation(summary = "Find all posts by the user", description = "Retrieve a list of all the posts by the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all posts retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<PostDTO>> getAllPostByUser(HttpServletRequest request);

    @Operation(summary = "Delete a post", description = "Delete a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Void> deletePost(UUID postId);
}
