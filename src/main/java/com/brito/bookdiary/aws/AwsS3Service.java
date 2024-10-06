package com.brito.bookdiary.aws;

import com.brito.bookdiary.exception.AwsConnectionException;
import com.brito.bookdiary.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Client s3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;


    public String getPostContent(String key) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            byte[] objectBytes = s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray();

            return new String(objectBytes, StandardCharsets.UTF_8);

        } catch (NoSuchKeyException e) {
            throw new ResourceNotFoundException(String.format("Arquivo não encontrado no S3: %s", key) );
        } catch (S3Exception e) {
            throw new AwsConnectionException(String.format("Erro ao acessar o bucket S3: %s", e.awsErrorDetails().errorMessage()));
        }
    }
}
