package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;

public class CreateQrcodeServiceImpl implements CreateQrcodeUseCase {
    private final QrcodeProviderPort qrcodeProviderPort;
    private final LoggerPort logger;

    public CreateQrcodeServiceImpl(
            QrcodeProviderPort qrcodeProviderPort,
            LoggerPort logger
    ) {
        this.qrcodeProviderPort = qrcodeProviderPort;
        this.logger = logger;
    }

    @Override
    public void run(QrCode qrCode) {
        logger.info("teste");
        byte[] qrcodePdfImage = qrcodeProviderPort.generatePng(qrCode.getUrl());
        IO.println(qrcodePdfImage.length);
    }
}
