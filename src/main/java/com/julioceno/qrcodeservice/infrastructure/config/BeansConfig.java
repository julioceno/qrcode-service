package com.julioceno.qrcodeservice.infrastructure.config;

import com.julioceno.qrcodeservice.core.application.service.CreateQrcodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.service.QrcodeServiceImpl;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateQrcodeUseCase createQrcodeUseCase() {
        return new CreateQrcodeServiceImpl();
    }

    @Bean
    public QrcodeUseCase qrcodeUseCase(
            CreateQrcodeUseCase createQrcodeUseCase
    ) {
        return new QrcodeServiceImpl(createQrcodeUseCase);
    }
}
