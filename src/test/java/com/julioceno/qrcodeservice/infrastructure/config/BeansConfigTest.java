package com.julioceno.qrcodeservice.infrastructure.config;

import com.julioceno.qrcodeservice.core.application.ports.out.FilesProviderPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerFactoryPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.application.service.CreateQrcodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.service.GetQrCodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.service.QrcodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.GetQrCodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BeansConfigTest {

    @Test
    void createQrcodeUseCaseShouldReturnCorrectImplementation() {
        QrcodeProviderPort qrcodeProviderPort = mock(QrcodeProviderPort.class);
        FilesProviderPort filesProviderPort = mock(FilesProviderPort.class);
        QrCodeRepositoryPort repository = mock(QrCodeRepositoryPort.class);
        LoggerFactoryPort loggerFactoryPort = mock(LoggerFactoryPort.class);
        when(loggerFactoryPort.getLogger(any())).thenReturn(mock(LoggerPort.class));

        BeansConfig config = new BeansConfig();
        CreateQrcodeUseCase bean = config.createQrcodeUseCase(
                qrcodeProviderPort,
                filesProviderPort,
                repository,
                loggerFactoryPort
        );

        assertInstanceOf(CreateQrcodeServiceImpl.class, bean);
    }

    @Test
    void getQrCodeUseCaseShouldReturnCorrectImplementation() {
        QrCodeRepositoryPort repository = mock(QrCodeRepositoryPort.class);
        LoggerFactoryPort loggerFactoryPort = mock(LoggerFactoryPort.class);
        when(loggerFactoryPort.getLogger(any())).thenReturn(mock(LoggerPort.class));

        BeansConfig config = new BeansConfig();
        GetQrCodeUseCase bean = config.getQrCodeUseCase(
                repository,
                loggerFactoryPort
        );

        assertInstanceOf(GetQrCodeServiceImpl.class, bean);
    }

    @Test
    void qrcodeUseCaseShouldReturnCorrectImplementation() {
        CreateQrcodeUseCase createUC = mock(CreateQrcodeUseCase.class);
        GetQrCodeUseCase getUC = mock(GetQrCodeUseCase.class);

        BeansConfig config = new BeansConfig();
        QrcodeUseCase bean = config.qrcodeUseCase(createUC, getUC);

        assertInstanceOf(QrcodeServiceImpl.class, bean);
    }
}
