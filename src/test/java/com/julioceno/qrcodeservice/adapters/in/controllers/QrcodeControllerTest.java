package com.julioceno.qrcodeservice.adapters.in.controllers;

import com.julioceno.qrcodeservice.adapters.in.dto.QrcodeCreateDTO;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QrcodeControllerTest {
    @Mock
    private QrcodeUseCase qrcodeUseCase;

    @InjectMocks
    private QrcodeController qrcodeController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/v1/qr");
        request.setServerName("localhost");
        request.setServerPort(8080);
        request.setScheme("http");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void create() {
        QrcodeCreateDTO dto = new QrcodeCreateDTO("url");
        QrCodeDTO qrCodeDTO = new QrCodeDTO("123", "url", "");
        when(qrcodeUseCase.create(any(QrCode.class))).thenReturn(qrCodeDTO);

        ResponseEntity<QrCodeDTO> response = qrcodeController.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(qrCodeDTO, response.getBody());
        assertNotNull(response.getHeaders().getLocation());
    }

    @Test
    void getById() {
        String id = "id";
        QrCodeDTO qrCodeDTO = new QrCodeDTO("123", "url", "");
        when(qrcodeUseCase.getById(id)).thenReturn(qrCodeDTO);
        ResponseEntity<QrCodeDTO> response = qrcodeController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(qrcodeUseCase).getById(id);
    }
}
