package com.julioceno.qrcodeservice.infrastructure.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.junit.jupiter.api.Assertions.*;

class CorrelationIdTest {

    @AfterEach
    void afterEach() {
        MDC.clear();
    }

    @Test
    void shouldReturnCorrelationIdFromMDC() {
        String expectedCorrelationId = "test-correlation-id";
        MDC.put(CorrelationId.HEADER_NAME, expectedCorrelationId);

        String result = CorrelationId.get();

        assertEquals(expectedCorrelationId, result);
    }

    @Test
    void shouldReturnNull_When_CorrelationIdNotInMDC() {
        String result = CorrelationId.get();

        assertNull(result);
    }

    @Test
    void shouldHaveCorrectHeaderName() {
        assertEquals("X-Correlation-Id", CorrelationId.HEADER_NAME);
    }
}