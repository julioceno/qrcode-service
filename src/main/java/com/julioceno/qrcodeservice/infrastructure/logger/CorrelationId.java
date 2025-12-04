package com.julioceno.qrcodeservice.infrastructure.logger;

import org.slf4j.MDC;

public final class CorrelationId {
    public static final String HEADER_NAME = "X-Correlation-Id";

    public CorrelationId() {
    }

    public static String get() {
        return MDC.get(HEADER_NAME);
    }
}
