package com.julioceno.qrcodeservice.core.application.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QrCodeGenerationExceptionTest {
    @Test
    void messageAndCauseAreStored() {
        Throwable cause = new RuntimeException("causa raiz");
        String msg = "Erro ao gerar QR code";
        QrCodeGenerationException ex = new QrCodeGenerationException(msg, cause);
        assertEquals(msg, ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}