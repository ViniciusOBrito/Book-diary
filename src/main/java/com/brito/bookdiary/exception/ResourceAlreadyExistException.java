package com.brito.bookdiary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ResourceAlreadyExistException extends BookDiaryException{

    private final String detail;

    public ResourceAlreadyExistException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Entity already exist");
        pb.setDetail(detail);

        return pb;
    }
}
