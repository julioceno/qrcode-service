package com.julioceno.qrcodeservice.infrastructure.aws;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

@Configuration
@Profile("local")
@Slf4j
public class LocalS3Initializer {
    private final S3Client s3client;
    private final String bucketName;

    public LocalS3Initializer(S3Client s3client, S3Properties props) {
        this.s3client = s3client;
        this.bucketName = props.getBucket();
    }

    @PostConstruct
    public void init() {
        try {
            s3client.createBucket(CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build()
            );
            log.info("Bucket '{}' created successfully", bucketName);
        } catch (Exception e) {
            log.warn("Bucket '{}' could not be created: {}", bucketName, e.getMessage());
        }
    }
}
