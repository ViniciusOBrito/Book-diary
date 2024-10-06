package com.brito.bookdiary.post;

import com.brito.bookdiary.auth.AuthService;
import com.brito.bookdiary.post.dto.PostDTO;
import com.brito.bookdiary.post.dto.PostRequestDTO;
import com.brito.bookdiary.security.TokenService;
import com.brito.bookdiary.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.brito.bookdiary.post.mock.mockPostFactory.mockListPostDTO;
import static com.brito.bookdiary.post.mock.mockPostFactory.mockPostRequestDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TokenService tokenService;
    @MockBean
    AuthService authService;
    @MockBean
    PostService postService;
    @MockBean
    UserService userService;

    private PostRequestDTO postRequestDTO;
    private List<PostDTO> postDTOList;

    @BeforeEach
    void setUp(){
        postRequestDTO = mockPostRequestDTO();
        postDTOList = mockListPostDTO();
    }


    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    @DisplayName("Should create a new post and return Created")
    void createPost_WithValidData_ReturnsCreated() throws Exception {
        String postJson = objectMapper.writeValueAsString(postRequestDTO);

        mockMvc.perform(post("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(postJson)
                    .with(csrf()))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    @DisplayName("Should return a list of all posts by the user")
    void getAllPostsByUser_ShouldReturnPostsListByUser() throws Exception {
        when(postService.getAllPostByUser(any())).thenReturn(postDTOList);

        mockMvc.perform(get("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(postDTOList.get(0).getId()))
                .andExpect(jsonPath("$[0].userId").value(postDTOList.get(0).getUserId()))
                .andExpect(jsonPath("$[0].comment").value(postDTOList.get(0).getComment()))
                .andExpect(jsonPath("$[0].fromPage").value(postDTOList.get(0).getFromPage()))
                .andExpect(jsonPath("$[0].toThePage").value(postDTOList.get(0).getToThePage()))
                .andExpect(jsonPath("$[0].timestamp").value(postDTOList.get(0).getTimestamp()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN","USER"})
    @DisplayName("Should delete a post and return No Content")
    void deletePost_WithValidData_ReturnsNoContent() throws Exception {
        when(postService.findOrThrow(any())).thenReturn(new Post());

        mockMvc.perform(delete("/api/posts/{postId}", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(csrf()))
                .andExpect(status().isNoContent());
    }

}