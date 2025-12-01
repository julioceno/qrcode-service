package com.julioceno.qrcodeservice.core.application.ports.out;

public interface LoggerPort {
    void info(String message, Object... params);
    void warn(String message, Object... params);
    void error(String message, Object... params);
}
