package com.julioceno.qrcodeservice.infrastructure.aws;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class S3ConfigTest {

    @Test
    void shouldBuildClientWithoutEndpoint() {
        S3Properties props = new S3Properties();
        props.setRegion("us-east-1");
        props.setEndpoint(null);

        S3ClientBuilder builder = mock(S3ClientBuilder.class);
        S3Client client = mock(S3Client.class);

        when(builder.region(any())).thenReturn(builder);
        when(builder.credentialsProvider(any())).thenReturn(builder);
        when(builder.build()).thenReturn(client);

        try (MockedStatic<S3Client> mocked = mockStatic(S3Client.class)) {
            mocked.when(S3Client::builder).thenReturn(builder);
            S3Config config = new S3Config();
            config.s3Client(props);

            verify(builder, times(0)).endpointOverride(any());
            verify(builder, times(0)).forcePathStyle(anyBoolean());
            verify(builder, times(1)).build();
        }
    }

    @Test
    void shouldBuildClientWithEndpoint() {
        S3Properties props = new S3Properties();
        props.setRegion("us-east-1");
        props.setEndpoint("http://localhost:4566");

        S3ClientBuilder builder = mock(S3ClientBuilder.class);
        S3Client client = mock(S3Client.class);

        when(builder.region(any())).thenReturn(builder);
        when(builder.credentialsProvider(any())).thenReturn(builder);
        when(builder.endpointOverride(any())).thenReturn(builder);
        when(builder.forcePathStyle(anyBoolean())).thenReturn(builder);
        when(builder.build()).thenReturn(client);

        try (MockedStatic<S3Client> mocked = mockStatic(S3Client.class)) {
            mocked.when(S3Client::builder).thenReturn(builder);
            S3Config config = new S3Config();
            config.s3Client(props);
            verify(builder, times(1)).endpointOverride(URI.create("http://localhost:4566"));
            verify(builder, times(1)).forcePathStyle(true);
            verify(builder, times(1)).build();
        }
    }
}
