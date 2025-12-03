package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.ports.out.FilesProviderPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;

import java.util.UUID;

public class CreateQrcodeServiceImpl implements CreateQrcodeUseCase {
    private final QrcodeProviderPort qrcodeProviderPort;
    private final FilesProviderPort filesProviderPort;
    private final QrCodeRepositoryPort qrCodeRepositoryPort;
    private final LoggerPort logger;

    public CreateQrcodeServiceImpl(
            QrcodeProviderPort qrcodeProviderPort,
            FilesProviderPort filesProviderPort,
            QrCodeRepositoryPort qrCodeRepositoryPort,
            LoggerPort logger
    ) {
        this.qrcodeProviderPort = qrcodeProviderPort;
        this.filesProviderPort = filesProviderPort;
        this.qrCodeRepositoryPort = qrCodeRepositoryPort;
        this.logger = logger;
    }

    @Override
    public QrCodeDTO run(QrCode qrCode) {
        logger.info("CreateQrcodeServiceImpl.run - start ");

        byte[] qrcodePdfImage = qrcodeProviderPort.generatePng(qrCode.getUrl());
        String qrCodeUrl = filesProviderPort.uploadFile(qrcodePdfImage, UUID.randomUUID().toString());

        qrCode.setQrcodeUrl(qrCodeUrl);
        QrCode entity = qrCodeRepositoryPort.save(qrCode);
        logger.info("CreateQrcodeServiceImpl.run - end ");

        return new QrCodeDTO(entity);
    }
}
