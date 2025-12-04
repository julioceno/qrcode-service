package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.exception.NotFoundException;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetQrCodeServiceImplTest {
    @Mock
    private QrCodeRepositoryPort qrCodeRepositoryPort;

    @Mock
    private LoggerPort logger;

    @InjectMocks
    private GetQrCodeServiceImpl getQrCodeServiceImpl;

    @Test
    void shouldGetQrCodeById() {
        String id = "id";
        QrCode qrCode = new QrCode();
        qrCode.setId(id);

        when(qrCodeRepositoryPort.findById(id)).thenReturn(Optional.of(qrCode));

        QrCodeDTO result = getQrCodeServiceImpl.run(id);

        verify(qrCodeRepositoryPort).findById(id);
        assertEquals(qrCode.getId(), result.getId());
    }

    @Test
    void shouldThrowNotFoundException_When_QrCodeNotFound() {
        String id = "id";
        when(qrCodeRepositoryPort.findById(id)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> getQrCodeServiceImpl.run(id));

        assertEquals(String.format("QrCode with id %s not found", id), ex.getMessage());
        verify(qrCodeRepositoryPort).findById("s");
    }
}