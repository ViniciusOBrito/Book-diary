package com.brito.bookdiary.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AwsSnsService {

    private final AmazonSNS amazonSNS;
    @Qualifier("postEventsTopic")
    private final Topic topic;

    public void publish(String message){

        this.amazonSNS.publish(topic.getTopicArn(), message);
    }
}
