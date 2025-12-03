package com.julioceno.qrcodeservice.infrastructure.config;

import com.julioceno.qrcodeservice.core.application.ports.out.FilesProviderPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerFactoryPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.application.service.CreateQrcodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.service.GetQrCodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.service.QrcodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.GetQrCodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateQrcodeUseCase createQrcodeUseCase(
            QrcodeProviderPort qrcodeProviderPort,
            FilesProviderPort filesProviderPort,
            QrCodeRepositoryPort qrCodeRepositoryPort,
            LoggerFactoryPort loggerFactoryPort
    ) {
        return new CreateQrcodeServiceImpl(
                qrcodeProviderPort,
                filesProviderPort,
                qrCodeRepositoryPort,
                loggerFactoryPort.getLogger(CreateQrcodeServiceImpl.class)
        );
    }

    @Bean
    public GetQrCodeUseCase getQrCodeUseCase(
            QrCodeRepositoryPort qrCodeRepositoryPort,
            LoggerFactoryPort loggerFactoryPort
    ) {
        return new GetQrCodeServiceImpl(
                qrCodeRepositoryPort,
                loggerFactoryPort.getLogger(GetQrCodeServiceImpl.class)
        );
    }

    @Bean
    public QrcodeUseCase qrcodeUseCase(
            CreateQrcodeUseCase createQrcodeUseCase,
            GetQrCodeUseCase getQrCodeUseCase
    ) {
        return new QrcodeServiceImpl(
                createQrcodeUseCase,
                getQrCodeUseCase
        );
    }
}
