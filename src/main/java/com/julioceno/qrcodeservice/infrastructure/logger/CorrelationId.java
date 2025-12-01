package com.julioceno.qrcodeservice.infrastructure.logger;

import org.slf4j.MDC;

public final class CorrelationId {
    public static final String CORRELATION_ID = "X-Correlation-Id";

    public CorrelationId() {
    }

    public static String getCorrelationId() {
        return MDC.get(CORRELATION_ID);
    }
}
