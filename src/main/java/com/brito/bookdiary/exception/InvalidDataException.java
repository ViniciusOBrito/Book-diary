package com.brito.bookdiary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidDataException extends BookDiaryException{

    private final String detail;

    public InvalidDataException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Invalid data");
        pb.setDetail(detail);

        return pb;
    }
}
