package com.julioceno.qrcodeservice.core.application.ports.out;

public interface LoggerFactoryPort {
    LoggerPort getLogger(Class<?> clazz);
}
