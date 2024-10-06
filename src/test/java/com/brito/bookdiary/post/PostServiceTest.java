package com.brito.bookdiary.post;

import com.brito.bookdiary.aws.AwsS3Service;
import com.brito.bookdiary.aws.AwsSnsService;
import com.brito.bookdiary.book.BookService;
import com.brito.bookdiary.post.dto.PostDTO;
import com.brito.bookdiary.post.dto.PostRespondeDTO;
import com.brito.bookdiary.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.brito.bookdiary.book.mock.BookMockFactory.mockBook;
import static com.brito.bookdiary.post.mock.mockPostFactory.*;
import static com.brito.bookdiary.user.MockUserFactory.mockUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PostServiceTest {

    @Mock
    BookService bookService;
    @Mock
    PostRepository postRepository;
    @Mock
    TokenService tokenService;
    @Mock
    AwsSnsService awsSnsService;
    @Mock
    HttpServletRequest request;
    @Mock
    AwsS3Service awsS3Service;
    @InjectMocks
    PostService postService;

    @Test
    @DisplayName("Should create a new post when successful")
    void createPost_WhenSuccessful(){
        when(tokenService.getUserFromRequest(any())).thenReturn(mockUser());
        when(bookService.findOrThrow(any())).thenReturn(mockBook());

        when(postRepository.save(any(Post.class))).thenReturn(mockPost());
        doNothing().when(awsSnsService).publish(anyString());

        PostRespondeDTO result = postService.createPost(mockPostRequestDTO(),request);

        assertNotNull(result);
        assertEquals(mockPostRespondeDTO().comment(), result.comment());
        assertEquals(mockPostRespondeDTO().fromPage(), result.fromPage());
        assertEquals(mockPostRespondeDTO().toThePage(), result.toThePage());
    }

    @Test
    @DisplayName("Should list all posts by the user")
    void getAllPostsByUser_ReturnsPostListByTheUser(){
        when(tokenService.getUserFromRequest(any())).thenReturn(mockUser());
        when(awsS3Service.getPostContent(anyString())).thenReturn(mockJson());

        List<PostDTO> result = postService.getAllPostByUser(request);

        assertNotNull(result);
        assertEquals(mockListPostDTO().size(), result.size());
    }

    @Test
    @DisplayName("Should delete a post when successful")
    void deletePost_WhenSuccessful(){
        when(postRepository.findById(any())).thenReturn(Optional.of(mockPost()));
        doNothing().when(postRepository).delete(any(Post.class));
        doNothing().when(awsSnsService).publish(anyString());

        postService.deletePost(UUID.randomUUID());

        verify(postRepository, times(1)).delete(any(Post.class));
    }

}