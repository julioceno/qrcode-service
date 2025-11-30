package com.julioceno.qrcodeservice.core.application.usecases;

import com.julioceno.qrcodeservice.core.application.dto.CreateQRcodeDTO;
import com.julioceno.qrcodeservice.core.domain.QrCode;

public interface CreateQrcodeUseCase {
    void run(QrCode qrCode);
}
