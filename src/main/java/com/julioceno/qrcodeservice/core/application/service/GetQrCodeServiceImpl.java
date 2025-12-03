package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.exception.NotFoundException;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.usecases.GetQrCodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;

import java.util.Optional;

public class GetQrCodeServiceImpl implements GetQrCodeUseCase {

    private final QrCodeRepositoryPort qrCodeRepositoryPort;
    private final LoggerPort logger;

    public GetQrCodeServiceImpl(QrCodeRepositoryPort qrCodeRepositoryPort, LoggerPort logger) {
        this.qrCodeRepositoryPort = qrCodeRepositoryPort;
        this.logger = logger;
    }

    @Override
    public QrCodeDTO run(String id) {
        logger.info("GetQrCodeServiceImpl.run - start - id [{}]", id);

        Optional<QrCode> qrCode = qrCodeRepositoryPort.findById(id);
        if (qrCode.isEmpty()) {
            logger.warn("GetQrCodeServiceImpl.run - QrCode not found for id [{}]", id);
            throw new NotFoundException(String.format("QrCode with id %s not found", id));
        }
        logger.info("GetQrCodeServiceImpl.run - QrCode found successfully for id [{}]", id);

        QrCodeDTO response = new QrCodeDTO(qrCode.get());
        logger.debug("GetQrCodeServiceImpl.run - QrCodeDTO [{}]", response);

        logger.info("GetQrCodeServiceImpl.run - end - id [{}]", id);
        return response;
    }
}
