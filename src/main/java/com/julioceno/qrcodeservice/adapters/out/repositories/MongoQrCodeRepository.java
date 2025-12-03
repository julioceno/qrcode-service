package com.julioceno.qrcodeservice.adapters.out.repositories;

import com.julioceno.qrcodeservice.adapters.out.entities.QrCodeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoQrCodeRepository extends MongoRepository<QrCodeEntity, String> {
}
