package com.julioceno.qrcodeservice.adapters.out.repositories;

import com.julioceno.qrcodeservice.adapters.out.entities.QrCodeEntity;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<QrCode> findById(String id) {
        Optional<QrCodeEntity> qrCodeEntity = mongoQrCodeRepository.findById(id);
        return qrCodeEntity.map(this::toDomain);
    }

    private QrCode toDomain(QrCodeEntity qrCodeEntity) {
        return new QrCode(
                qrCodeEntity.getId(),
                qrCodeEntity.getUrl(),
                qrCodeEntity.getQrcodeUrl()
        );
    }
}
