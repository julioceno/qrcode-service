package com.julioceno.qrcodeservice.core.application.usecases;

import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;

public interface CreateQrcodeUseCase {
    QrCodeDTO run(QrCode qrCode);
}
