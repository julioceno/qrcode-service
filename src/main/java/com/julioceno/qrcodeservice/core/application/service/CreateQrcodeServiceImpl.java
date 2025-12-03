package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.ports.out.FilesProviderPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;

import java.util.UUID;

public class CreateQrcodeServiceImpl implements CreateQrcodeUseCase {
    private final QrcodeProviderPort qrcodeProviderPort;
    private final FilesProviderPort filesProviderPort;
    private final LoggerPort logger;

    public CreateQrcodeServiceImpl(
            QrcodeProviderPort qrcodeProviderPort,
            FilesProviderPort filesProviderPort,
            LoggerPort logger
    ) {
        this.qrcodeProviderPort = qrcodeProviderPort;
        this.filesProviderPort = filesProviderPort;
        this.logger = logger;
    }

    @Override
    public void run(QrCode qrCode) {
        logger.info("CreateQrcodeServiceImpl.run - start ");
        byte[] qrcodePdfImage = qrcodeProviderPort.generatePng(qrCode.getUrl());
        IO.println(qrcodePdfImage.length);

        filesProviderPort.uploadFile(qrcodePdfImage, String.format("%s.png", UUID.randomUUID().toString()));

        logger.info("CreateQrcodeServiceImpl.run - end ");
    }
}
