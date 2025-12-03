package com.julioceno.qrcodeservice.core.application.usecases;

import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;

public interface GetQrCodeUseCase {
    QrCodeDTO run(String id);
}
