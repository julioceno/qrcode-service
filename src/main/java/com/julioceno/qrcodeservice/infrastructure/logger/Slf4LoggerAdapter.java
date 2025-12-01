package com.julioceno.qrcodeservice.infrastructure.logger;


import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Slf4LoggerAdapter implements LoggerPort {
    private final Logger logger;

    public Slf4LoggerAdapter(Class<?> logger) {
        this.logger = LoggerFactory.getLogger(logger);
    }

    @Override
    public void info(String message) {
        logger.info(appendCorrelationId(message));
    }

    @Override
    public void warn(String message) {
        logger.warn(appendCorrelationId(message));
    }

    @Override
    public void error(String message) {
        logger.error(appendCorrelationId(message));
    }

    private String appendCorrelationId(String message) {
        String correlationId = MDC.get(CorrelationId.CORRELATION_ID);

        if (correlationId == null || correlationId.isBlank()) {
            return message;
        }

        return String.format("[correlationId=%s] %s", correlationId, message);
    }
}
