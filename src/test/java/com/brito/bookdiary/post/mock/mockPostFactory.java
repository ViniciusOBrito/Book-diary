package com.brito.bookdiary.post.mock;

import com.brito.bookdiary.post.Post;
import com.brito.bookdiary.post.dto.PostDTO;
import com.brito.bookdiary.post.dto.PostRequestDTO;
import com.brito.bookdiary.post.dto.PostRespondeDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.brito.bookdiary.book.mock.BookMockFactory.mockBook;
import static com.brito.bookdiary.user.MockUserFactory.mockUser;

public class mockPostFactory {

    public static PostRequestDTO mockPostRequestDTO(){
        return new PostRequestDTO(
                UUID.randomUUID(),
                "Comment",
                10L,
                11L
        );
    }

    public static PostRespondeDTO mockPostRespondeDTO(){
        return new PostRespondeDTO(mockPost());
    }

    public static Post mockPost(){
        Post post = new Post();
        post.setId(UUID.randomUUID());
        post.setUserAuthor(mockUser());
        post.setComment("Comment");
        post.setFromPage(10L);
        post.setToThePage(11L);
        post.setBook(mockBook());

        return post;
    }

    public static PostDTO mockPostDTO(){
        return new PostDTO(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                10,
                11,
                "Comment example",
                "2024-10-03T22:41:32.129813900"
        );
    }

    public static List<PostDTO> mockListPostDTO(){
        return Collections.nCopies(2, mockPostDTO());
    }

    public static String mockJson(){
        return "[\n" +
                "  {\n" +
                "    \"fromPage\": 10,\n" +
                "    \"toThePage\": 20,\n" +
                "    \"action\": \"ADD\",\n" +
                "    \"comment\": \"livro bom te amo cammus \",\n" +
                "    \"id\": \"c2d69d43-d0d2-4000-a0ec-0f3cfc50538c\",\n" +
                "    \"userId\": \"d23f766a-0c8d-428b-8ad6-f0bdfa5f10d1\",\n" +
                "    \"timestamp\": \"2024-10-03T22:41:32.129813900\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"fromPage\": 10,\n" +
                "    \"toThePage\": 20,\n" +
                "    \"action\": \"ADD\",\n" +
                "    \"comment\": \"livro bom te amo cammus \",\n" +
                "    \"id\": \"c2d69d43-d0d2-4000-a0ec-0f3cfc50538c\",\n" +
                "    \"userId\": \"d23f766a-0c8d-428b-8ad6-f0bdfa5f10d1\",\n" +
                "    \"timestamp\": \"2024-10-03T22:41:32.129813900\"\n" +
                "  }\n" +
                "]";
    }
}
