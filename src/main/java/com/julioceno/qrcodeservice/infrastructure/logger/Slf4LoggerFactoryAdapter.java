package com.julioceno.qrcodeservice.infrastructure.logger;

import com.julioceno.qrcodeservice.core.application.ports.out.LoggerFactoryPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import org.springframework.stereotype.Component;

@Component
public class Slf4LoggerFactoryAdapter implements LoggerFactoryPort {

    @Override
    public LoggerPort getLogger(Class<?> clazz) {
        return new Slf4LoggerAdapter(clazz);
    }
}
