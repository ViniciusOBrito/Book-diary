package com.brito.bookdiary.exception.handler;

import com.brito.bookdiary.exception.BookDiaryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookDiaryException.class)
    public ProblemDetail handleGestaoLucroException(BookDiaryException e) {
        return e.toProblemDetail();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParams(f.getField(), f.getDefaultMessage()));

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("The request parameters are incorrect\n");
        pb.setProperty("invalid-fields", fieldErros);

        return pb;
    }
    private record InvalidParams(String name, String reason){}
}
