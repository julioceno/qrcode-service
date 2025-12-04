package com.julioceno.qrcodeservice.infrastructure.aws;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalS3InitializerTest {

    @Mock
    private S3Client s3Client;

    private LocalS3Initializer initializer;

    @BeforeEach
    void setUp() {
        S3Properties props = new S3Properties();
        props.setBucket("local-bucket");
        initializer = new LocalS3Initializer(s3Client, props);
    }

    @Test
    void initShouldCreateBucketSuccessfully() {
        initializer.init();
        verify(s3Client, times(1)).createBucket(any(CreateBucketRequest.class));
    }

    @Test
    void initShouldHandleException() {
        doThrow(new RuntimeException("error")).when(s3Client).createBucket(any(CreateBucketRequest.class));
        initializer.init();
        verify(s3Client, times(1)).createBucket(any(CreateBucketRequest.class));
    }
}
