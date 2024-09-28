package com.brito.bookdiary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Book Diary System",
        description = "REST API for post or anote infos about books daily."))
public class BookDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookDiaryApplication.class, args);
    }

}
