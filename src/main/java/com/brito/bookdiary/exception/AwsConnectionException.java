package com.brito.bookdiary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AwsConnectionException extends BookDiaryException{

    private final String detail;

    public AwsConnectionException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE);

        pb.setTitle("Error in connection with AWS");
        pb.setDetail(detail);

        return pb;
    }
}
