package com.julioceno.qrcodeservice.core.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    @Test
    void messageIsStored() {
        String msg = "Recurso não encontrado";
        NotFoundException ex = new NotFoundException(msg);
        assertEquals(msg, ex.getMessage());
    }
}