package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.ports.out.FilesProviderPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateQrcodeServiceImplTest {
    @Mock
    private QrcodeProviderPort qrcodeProviderPort;

    @Mock
    private FilesProviderPort filesProviderPort;

    @Mock
    private QrCodeRepositoryPort qrCodeRepositoryPort;

    @Mock
    private LoggerPort loggerPort;

    @InjectMocks
    private CreateQrcodeServiceImpl createQrcodeServiceImpl;

    @Test
    void shouldCreateQrcode() {
        String qrcodeUrl = "qrcodeUrl";
        QrCode qrCodeToCreate = new QrCode(
                null,
                "url",
                null
        );

        when(qrcodeProviderPort.generatePng(qrCodeToCreate.getUrl())).thenReturn(new byte[0]);
        when(filesProviderPort.uploadFile(any(byte[].class), anyString())).thenReturn(qrcodeUrl);
        when(qrCodeRepositoryPort.save(qrCodeToCreate)).thenReturn(qrCodeToCreate);

        QrCodeDTO result = createQrcodeServiceImpl.run(qrCodeToCreate);

        verify(qrcodeProviderPort, times(1)).generatePng(qrCodeToCreate.getUrl());
        verify(filesProviderPort, times(1)).uploadFile(any(byte[].class), any(String.class));
        verify(qrCodeRepositoryPort, times(1)).save(qrCodeToCreate);

        assertEquals(qrcodeUrl, result.getQrcodeUrl());
        assertEquals(qrCodeToCreate.getUrl(), result.getUrl());
    }
}