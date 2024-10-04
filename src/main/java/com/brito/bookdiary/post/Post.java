package com.brito.bookdiary.post;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TAB_POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JsonManagedReference
    private User userAuthor;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @Column(name = "comment", nullable = false, length = 255)
    private String comment;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "from_page", nullable = false)
    private Long fromPage;

    @Column(name = "to_the_page", nullable = false)
    private Long toThePage;

    public String toString(String action){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("userId", this.userAuthor.getId());
        json.put("comment", this.comment);
        json.put("fromPage", this.fromPage);
        json.put("toThePage", this.toThePage);
        json.put("timestamp", this.timestamp);
        json.put("action", action);

        return json.toString();
    }
}
