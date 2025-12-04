package com.julioceno.qrcodeservice.adapters.in.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PingControllerTest {

    @InjectMocks
    private PingController pingController;

    @Test
    void ping() {
        ResponseEntity<String> response = pingController.ping();
        assertEquals("pong", response.getBody());
    }
}