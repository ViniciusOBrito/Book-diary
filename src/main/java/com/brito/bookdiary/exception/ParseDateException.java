package com.brito.bookdiary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ParseDateException extends BookDiaryException{

    private final String detail;

    public ParseDateException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Erro while parsing date");
        pb.setDetail(detail);

        return pb;
    }
}
