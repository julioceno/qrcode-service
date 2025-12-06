package com.julioceno.qrcodeservice.infrastructure.logger;

import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Slf4LoggerFactoryAdapterTest {

    @Test
    void getLogger_returnsLoggerAdapterInstance() {
        Slf4LoggerFactoryAdapter factory = new Slf4LoggerFactoryAdapter();
        LoggerPort logger = factory.getLogger(Slf4LoggerFactoryAdapterTest.class);

        assertNotNull(logger, "logger must not be null");
        assertInstanceOf(Slf4LoggerAdapter.class, logger, "should be a instance adapter");
    }
}