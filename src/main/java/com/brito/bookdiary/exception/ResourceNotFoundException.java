package com.brito.bookdiary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ResourceNotFoundException extends BookDiaryException{

    private final String detail;

    public ResourceNotFoundException(String detail){
        this.detail = detail;
    }


    @Override
    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Entity not found");
        pb.setDetail(detail);

        return pb;
    }
}
