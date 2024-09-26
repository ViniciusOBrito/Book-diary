package com.brito.bookdiary.post.dto;

import com.brito.bookdiary.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "Post Responde DTO", description = "DTO for responde containing infos about the post")
public record PostRespondeDTO(
        @Schema(description = "Id of the user that made the post", example = "3278f27a394b4....")
        UUID userAuthorId,
        @Schema(description = "Email of the user that made the post", example = "vinicius@example.com")
        String userAuthorEmail,
        @Schema(description = "Id of the book that the post is about", example = "3278f27a394b....")
        UUID bookId,
        @Schema(description = "Comment of the post", example = "I dont beleave they made that..")
        String comment,
        @Schema(description = "Timestamp that the post was made", example = "2024-09-25T19:28:06.4939996")
        LocalDateTime timestamp,
        @Schema(description = "Initial page that the post is about", example = "10")
        Long fromPage,
        @Schema(description = "Final page that the post is about", example = "11")
        Long toThePage
) {

    public PostRespondeDTO(Post post){
        this(
                post.getUserAuthor().getId(),
                post.getUserAuthor().getEmail(),
                post.getBook().getId(),
                post.getComment(),
                post.getTimestamp(),
                post.getFromPage(),
                post.getToThePage()
        );
    }
}
