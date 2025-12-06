package com.julioceno.qrcodeservice.core.application.exception;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void settersAndGettersWork() {
        ErrorResponse r = new ErrorResponse();
        LocalDateTime now = LocalDateTime.now();
        r.setTimestamp(now);
        r.setStatus(404);
        r.setError("Not Found");
        r.setMessage("Recurso não encontrado");
        r.setPath("/api/itens/1");
        r.setCorrelationId("cid-123");

        assertEquals(now, r.getTimestamp());
        assertEquals(404, r.getStatus());
        assertEquals("Not Found", r.getError());
        assertEquals("Recurso não encontrado", r.getMessage());
        assertEquals("/api/itens/1", r.getPath());
        assertEquals("cid-123", r.getCorrelationId());
    }

    @Test
    void allArgsConstructorSetsFields() {
        LocalDateTime t = LocalDateTime.of(2025, 1, 1, 12, 0);
        ErrorResponse r = new ErrorResponse(t, 500, "Error", "mensagem", "/path", "cid");
        assertEquals(t, r.getTimestamp());
        assertEquals(500, r.getStatus());
        assertEquals("Error", r.getError());
        assertEquals("mensagem", r.getMessage());
        assertEquals("/path", r.getPath());
        assertEquals("cid", r.getCorrelationId());
    }
}