package com.julioceno.qrcodeservice.infrastructure.exception;

import com.julioceno.qrcodeservice.core.application.exception.BadRequestException;
import com.julioceno.qrcodeservice.core.application.exception.ErrorResponse;
import com.julioceno.qrcodeservice.core.application.exception.NotFoundException;
import com.julioceno.qrcodeservice.core.application.exception.QrCodeGenerationException;
import com.julioceno.qrcodeservice.infrastructure.logger.CorrelationId;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private static final String CORRELATION_ID = "test-correlation-id";
    private static final String REQUEST_URI = "/v1/api";

    @BeforeEach
    void setUp() {
        when(request.getRequestURI()).thenReturn(REQUEST_URI);
    }

    @Test
    void badRequestException() {
        BadRequestException ex = new BadRequestException("Bad request message");

        try(MockedStatic<CorrelationId> correlationIdMock = mockStatic(CorrelationId.class)) {
            correlationIdMock.when(CorrelationId::get).thenReturn(CORRELATION_ID);

            ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(ex, request);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

            ErrorResponse errorResponse = response.getBody();
            assertNotNull(errorResponse);
            assertEquals(400, errorResponse.getStatus());
            assertEquals("Bad Request", errorResponse.getError());
            assertEquals("Bad request message", errorResponse.getMessage());
            assertEquals(REQUEST_URI, errorResponse.getPath());
            assertEquals(CORRELATION_ID, errorResponse.getCorrelationId());
        }
    }


    @Test
    void validationFailedException() {
        FieldError fieldError = new FieldError("obj", "name", "must not be blank");
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        try (MockedStatic<CorrelationId> correlationIdMock = mockStatic(CorrelationId.class)) {
            correlationIdMock.when(CorrelationId::get).thenReturn(CORRELATION_ID);

            ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(methodArgumentNotValidException, request);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

            ErrorResponse errorResponse = response.getBody();
            assertNotNull(errorResponse);
            assertEquals(400, errorResponse.getStatus());
            assertEquals("Validation Failed", errorResponse.getError());
            assertEquals("name: must not be blank", errorResponse.getMessage());
            assertEquals(REQUEST_URI, errorResponse.getPath());
            assertEquals(CORRELATION_ID, errorResponse.getCorrelationId());
        }
    }

    @Test
    void qrCodeGenerationException() {
        Throwable cause = new Throwable();
        QrCodeGenerationException ex = new QrCodeGenerationException("QR failed", cause);

        try (MockedStatic<CorrelationId> correlationIdMock = mockStatic(CorrelationId.class)) {
            correlationIdMock.when(CorrelationId::get).thenReturn(CORRELATION_ID);

            ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleQrCodeException(ex, request);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

            ErrorResponse errorResponse = response.getBody();
            assertNotNull(errorResponse);
            assertEquals(500, errorResponse.getStatus());
            assertEquals("QR Code Generation Error", errorResponse.getError());
            assertEquals("QR failed", errorResponse.getMessage());
            assertEquals(REQUEST_URI, errorResponse.getPath());
            assertEquals(CORRELATION_ID, errorResponse.getCorrelationId());
        }
    }

    @Test
    void notFoundException() {
        NotFoundException ex = new NotFoundException("Not found");

        try (MockedStatic<CorrelationId> correlationIdMock = mockStatic(CorrelationId.class)) {
            correlationIdMock.when(CorrelationId::get).thenReturn(CORRELATION_ID);

            ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleQrCodeException(ex, request);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

            ErrorResponse errorResponse = response.getBody();
            assertNotNull(errorResponse);
            assertEquals(404, errorResponse.getStatus());
            assertEquals("Not Found", errorResponse.getError());
            assertEquals("Not found", errorResponse.getMessage());
            assertEquals(REQUEST_URI, errorResponse.getPath());
            assertEquals(CORRELATION_ID, errorResponse.getCorrelationId());
        }
    }

    @Test
    void runtimeExceptionHandler() {
        Exception ex = new RuntimeException("boom");

        try (MockedStatic<CorrelationId> correlationIdMock = mockStatic(CorrelationId.class)) {
            correlationIdMock.when(CorrelationId::get).thenReturn(CORRELATION_ID);

            ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleAll(ex, request);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

            ErrorResponse errorResponse = response.getBody();
            assertNotNull(errorResponse);
            assertEquals(500, errorResponse.getStatus());
            assertEquals("Internal Server Error", errorResponse.getError());
            assertEquals("An unexpected error occurred", errorResponse.getMessage());
            assertEquals(REQUEST_URI, errorResponse.getPath());
            assertEquals(CORRELATION_ID, errorResponse.getCorrelationId());
        }
    }
}