package com.julioceno.qrcodeservice.infrastructure.aws;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Utilities;

import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class S3FilesAdapterTest {

    @Mock
    private S3Client s3Client;

    @Mock
    private S3Utilities s3Utilities;

    private S3FilesAdapter s3FilesAdapter;

    @BeforeEach
    void setUp() {
        S3Properties props = new S3Properties();
        props.setBucket("my-test-bucket");

        when(s3Client.utilities()).thenReturn(s3Utilities);

        s3FilesAdapter = new S3FilesAdapter(s3Client, props);
    }

    @Test
    void uploadFile() throws Exception {
        byte[] fileBytes = "file".getBytes();
        String fileName = "qr.png";
        URL expectedUrl = new URI("http://mock.s3" + fileName).toURL();

        when(s3Utilities.getUrl(any(GetUrlRequest.class))).thenReturn(expectedUrl);

        String result = s3FilesAdapter.uploadFile(fileBytes, fileName);

        assertEquals(expectedUrl.toString(), result);

        verify(s3Client).putObject(
                any(PutObjectRequest.class),
                any(RequestBody.class)
        );

        verify(s3Utilities).getUrl(any(GetUrlRequest.class));
    }
}
