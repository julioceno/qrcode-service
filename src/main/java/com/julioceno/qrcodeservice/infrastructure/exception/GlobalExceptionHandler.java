package com.julioceno.qrcodeservice.infrastructure.exception;

import com.julioceno.qrcodeservice.core.application.exception.BadRequestException;
import com.julioceno.qrcodeservice.core.application.exception.ErrorResponse;
import com.julioceno.qrcodeservice.core.application.exception.NotFoundException;
import com.julioceno.qrcodeservice.core.application.exception.QrCodeGenerationException;
import com.julioceno.qrcodeservice.infrastructure.logger.CorrelationId;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleException(BadRequestException ex, HttpServletRequest request) {
        String correlationId = CorrelationId.get();

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI(),
                correlationId
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String correlationId = CorrelationId.get();

        String messages = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> String.format("%s: %s", err.getField(), err.getDefaultMessage()))
                .collect(Collectors.joining(", "));

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                messages,
                request.getRequestURI(),
                correlationId
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(QrCodeGenerationException.class)
    public ResponseEntity<ErrorResponse> handleQrCodeException(
            QrCodeGenerationException ex,
            HttpServletRequest request
    ) {
        String correlationId = CorrelationId.get();

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "QR Code Generation Error",
                ex.getMessage(),
                request.getRequestURI(),
                correlationId
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQrCodeException(
            NotFoundException ex,
            HttpServletRequest request
    ) {
        String correlationId = CorrelationId.get();

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI(),
                correlationId
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest request) {
        String correlationId = CorrelationId.get();
        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred",
                request.getRequestURI(),
                correlationId
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    };
}
