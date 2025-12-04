package com.julioceno.qrcodeservice.infrastructure.aws;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S3PropertiesTest {

    @Test
    void shouldSetAndGetProperties() {
        S3Properties props = new S3Properties();

        props.setRegion("us-east-1");
        props.setBucket("my-bucket");
        props.setEndpoint("http://localhost:4566");

        assertEquals("us-east-1", props.getRegion());
        assertEquals("my-bucket", props.getBucket());
        assertEquals("http://localhost:4566", props.getEndpoint());
    }
}
