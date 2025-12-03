package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.GetQrCodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import com.julioceno.qrcodeservice.core.domain.QrCodeDTO;

public class QrcodeServiceImpl implements QrcodeUseCase  {
    private final CreateQrcodeUseCase createQrcodeUseCase;
    private final GetQrCodeUseCase  getQrCodeUseCase;

    public QrcodeServiceImpl(CreateQrcodeUseCase createQrcodeUseCase, GetQrCodeUseCase getQrCodeUseCase) {
        this.createQrcodeUseCase = createQrcodeUseCase;
        this.getQrCodeUseCase = getQrCodeUseCase;
    }

    @Override
    public QrCodeDTO create(QrCode qrCode) {
        return createQrcodeUseCase.run(qrCode);
    }

    @Override
    public QrCodeDTO getById(String id) {
        return getQrCodeUseCase.run(id);
    }
}
