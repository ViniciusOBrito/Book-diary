package com.brito.bookdiary.post.mock;

import com.brito.bookdiary.post.dto.PostDTO;
import com.brito.bookdiary.post.dto.PostRequestDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class mockPostFactory {

    public static PostRequestDTO mockPostRequestDTO(){
        return new PostRequestDTO(
                UUID.randomUUID(),
                "Comment example",
                10L,
                11L
        );
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
}
