package com.julioceno.qrcodeservice.infrastructure.logger;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class Slf4LoggerAdapterTest {

    private Slf4LoggerAdapter loggerAdapter;
    private ListAppender<ILoggingEvent> listAppender;
    private Logger logger;

    @BeforeEach
    void setUp() {
        loggerAdapter = new Slf4LoggerAdapter(Slf4LoggerAdapterTest.class);

        logger = (Logger) LoggerFactory.getLogger(Slf4LoggerAdapterTest.class);
        logger.setLevel(Level.DEBUG);

        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        MDC.clear();
    }

    @AfterEach
    void tearDown() {
        MDC.clear();
        logger.detachAppender(listAppender);
    }

    @Test
    void info() {
        String correlationId = "test-correlation-id";
        String message = "Test info message";
        MDC.put(CorrelationId.HEADER_NAME, correlationId);

        loggerAdapter.info(message);

        assertEquals(1, listAppender.list.size());
        ILoggingEvent logEvent = listAppender.list.getFirst();
        assertTrue(logEvent.getMessage().contains(correlationId));
        assertTrue(logEvent.getMessage().contains(message));
        assertEquals(ch.qos.logback.classic.Level.INFO, logEvent.getLevel());
    }

    @Test
    void debug() {
        String correlationId = "test-correlation-id";
        String message = "Test debug message";
        MDC.put(CorrelationId.HEADER_NAME, correlationId);

        loggerAdapter.debug(message);

        assertEquals(1, listAppender.list.size());
        ILoggingEvent logEvent = listAppender.list.getFirst();
        assertTrue(logEvent.getMessage().contains(correlationId));
        assertTrue(logEvent.getMessage().contains(message));
        assertEquals(ch.qos.logback.classic.Level.DEBUG, logEvent.getLevel());
    }

    @Test
    void warn() {
        String correlationId = "test-correlation-id";
        String message = "Test warn message";
        MDC.put(CorrelationId.HEADER_NAME, correlationId);

        loggerAdapter.warn(message);

        assertEquals(1, listAppender.list.size());
        ILoggingEvent logEvent = listAppender.list.getFirst();
        assertTrue(logEvent.getMessage().contains(correlationId));
        assertTrue(logEvent.getMessage().contains(message));
        assertEquals(ch.qos.logback.classic.Level.WARN, logEvent.getLevel());
    }

    @Test
    void error() {
        String correlationId = "test-correlation-id";
        String message = "Test error message";
        MDC.put(CorrelationId.HEADER_NAME, correlationId);

        loggerAdapter.error(message);

        assertEquals(1, listAppender.list.size());
        ILoggingEvent logEvent = listAppender.list.getFirst();
        assertTrue(logEvent.getMessage().contains(correlationId));
        assertTrue(logEvent.getMessage().contains(message));
        assertEquals(ch.qos.logback.classic.Level.ERROR, logEvent.getLevel());
    }
}