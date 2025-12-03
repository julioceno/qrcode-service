package com.julioceno.qrcodeservice.core.domain;

import java.util.Optional;

public interface QrCodeRepositoryPort {
    QrCode save(QrCode qrCode);
    Optional<QrCode> findById(String id);
}
