package com.brito.bookdiary.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO{

    private String id;
    private String userId;
    private int fromPage;
    private int toThePage;
    private String comment;
    private String timestamp;
}
