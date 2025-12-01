package com.julioceno.qrcodeservice.core.application.usecases;

import com.julioceno.qrcodeservice.core.domain.QrCode;

public interface QrcodeUseCase {
    void create(QrCode dto);
}
