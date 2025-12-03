package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.GetQrCodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QrcodeServiceImplTest {
    @Mock
    private CreateQrcodeUseCase createQrcodeUseCase;

    @Mock
    private GetQrCodeUseCase getQrCodeUseCase;

    @InjectMocks
    private QrcodeServiceImpl qrcodeService;

    @Test
    void create() {
        QrCode qrCode = new QrCode();
        qrcodeService.create(qrCode);

        verify(createQrcodeUseCase).run(qrCode);
    }

    @Test
    void getById() {
        String id = "id";
        qrcodeService.getById(id);

        verify(getQrCodeUseCase).run(id);
    }
}