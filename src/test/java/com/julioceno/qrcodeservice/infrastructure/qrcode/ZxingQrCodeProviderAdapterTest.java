package com.julioceno.qrcodeservice.infrastructure.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.julioceno.qrcodeservice.core.application.exception.QrCodeGenerationException;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerFactoryPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZxingQrCodeProviderAdapterTest {

    @Mock
    private LoggerFactoryPort loggerFactory;

    @Mock
    private LoggerPort logger;

    private ZxingQrCodeProviderAdapter adapter;

    private final String url = "mock";

    @BeforeEach
    void setUp() {
        when(loggerFactory.getLogger(ZxingQrCodeProviderAdapter.class)).thenReturn(logger);
        adapter = new ZxingQrCodeProviderAdapter(loggerFactory);
    }

    @Test
    void generatePng_shouldReturnNonEmptyBytes() {
        byte[] result = adapter.generatePng(url);

        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void shouldHandleThrow_When_WriterExceptionThrown() {
        try (MockedConstruction<QRCodeWriter> mocked = mockConstruction(QRCodeWriter.class, (mock, context) -> {
            try {
                when(mock.encode(anyString(), any(BarcodeFormat.class), anyInt(), anyInt()))
                        .thenThrow(new WriterException("encode fail"));
            } catch (WriterException e) {}
        })) {
            assertThrows(QrCodeGenerationException.class, () -> adapter.generatePng(url));
        }
    }

    @Test
    void shouldHandleThrow_When_MatrixToImageWriterExceptionThrown() {
        try (MockedStatic<MatrixToImageWriter> mockedStatic = mockStatic(MatrixToImageWriter.class)) {
            mockedStatic.when(() -> MatrixToImageWriter.writeToStream(any(BitMatrix.class), eq("PNG"), any(OutputStream.class)))
                    .thenThrow(new IOException("io fail"));

            assertThrows(QrCodeGenerationException.class, () -> adapter.generatePng(url));
        }
    }
}