package com.julioceno.qrcodeservice.infrastructure.aws;

import com.julioceno.qrcodeservice.core.application.ports.out.FilesProviderPort;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.net.URL;

@Configuration
public class S3FilesAdapter implements FilesProviderPort {
    private final S3Client s3client;
    private final String bucketName;

    public S3FilesAdapter(S3Properties props) {
        this.bucketName = props.getBucket();

        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(props.getRegion()))
                .credentialsProvider(DefaultCredentialsProvider.builder().build());

        if (props.getEndpoint() != null && !props.getEndpoint().isBlank()) {
            builder.endpointOverride(URI.create(props.getEndpoint()));
            builder.forcePathStyle(true);
        }

        this.s3client = builder.build();
    }

    @Override
    public String uploadFile(byte[] file, String fileName) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType("image/png") // TODO: alter this to dynamic prop
                .build();


        s3client.putObject(objectRequest, RequestBody.fromBytes(file));

        URL url = s3client.utilities().getUrl(GetUrlRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build());

        return url.toString();
    }
}
