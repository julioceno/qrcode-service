package com.julioceno.qrcodeservice.core.application.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {
    @Test
    void messageIsStored() {
        String msg = "Requisição inválida";
        BadRequestException ex = new BadRequestException(msg);
        assertEquals(msg, ex.getMessage());
    }
}