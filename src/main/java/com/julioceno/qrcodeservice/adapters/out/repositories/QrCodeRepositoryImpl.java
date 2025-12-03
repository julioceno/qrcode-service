package com.julioceno.qrcodeservice.adapters.out.repositories;

import com.julioceno.qrcodeservice.adapters.out.entities.QrCodeEntity;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
public class QrCodeRepositoryImpl implements QrCodeRepositoryPort {
    private final MongoQrCodeRepository mongoQrCodeRepository;

    public QrCodeRepositoryImpl(MongoQrCodeRepository mongoQrCodeRepository) {
        this.mongoQrCodeRepository = mongoQrCodeRepository;
    }

    @Override
    public QrCode save(QrCode qrCode) {
        QrCodeEntity entity = new QrCodeEntity(qrCode);
        QrCodeEntity qrCodeEntity = mongoQrCodeRepository.save(entity);
        return toDomain(qrCodeEntity);
    }

    private QrCode toDomain(QrCodeEntity qrCodeEntity) {
        return new QrCode(
                qrCodeEntity.getId(),
                qrCodeEntity.getUrl(),
                qrCodeEntity.getQrcodeUrl()
        );
    }
}
