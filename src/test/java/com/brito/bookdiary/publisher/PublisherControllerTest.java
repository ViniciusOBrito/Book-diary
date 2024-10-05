package com.brito.bookdiary.publisher;

import com.brito.bookdiary.auth.AuthService;
import com.brito.bookdiary.publisher.dto.PublisherRequestDTO;
import com.brito.bookdiary.publisher.dto.PublisherRespondeDTO;
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

import static com.brito.bookdiary.publisher.mock.PublisherMockFactory.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TokenService tokenService;
    @MockBean
    AuthService authService;
    @MockBean
    PublisherService publisherService;
    @MockBean
    UserService userService;

    private PublisherRequestDTO publisherRequestDTO;
    private List<PublisherRespondeDTO> publisherRespondeDTOList;

    @BeforeEach
    void setUp(){
        publisherRequestDTO = mockPublisherRequestDTO();
        publisherRespondeDTOList = mockListPublisherResponseDTO();
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    @DisplayName("Should return a list of all publishers")
    void getAllPublisher_ShouldReturnPublishersList() throws Exception {
        when(publisherService.getAllPublishers()).thenReturn(publisherRespondeDTOList);

        mockMvc.perform(get("/api/publishers")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(publisherRespondeDTOList.get(0).id().toString()))
                .andExpect(jsonPath("$[0].name").value(publisherRespondeDTOList.get(0).name()))
                .andExpect(jsonPath("$[0].email").value(publisherRespondeDTOList.get(0).email()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should create a new publisher and return Created")
    void createPublisher_WithValidData_ReturnsCreated() throws Exception {
        String publisherJson = objectMapper.writeValueAsString(publisherRequestDTO);

        mockMvc.perform(post("/api/publishers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(publisherJson)
                    .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Should update a publisher and return Ok")
    void updatePublisher_WithValidData_ReturnsOk() throws Exception {
        String publisherJson = objectMapper.writeValueAsString(publisherRequestDTO);

        mockMvc.perform(put("/api/publishers/{publisherId}", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(publisherJson)
                    .with(csrf()))
                .andExpect(status().isOk());
    }
}