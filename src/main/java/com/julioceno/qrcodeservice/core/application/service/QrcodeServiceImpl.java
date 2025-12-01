package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;

public class QrcodeServiceImpl implements QrcodeUseCase  {
    private final CreateQrcodeUseCase createQrcodeUseCase;

    public QrcodeServiceImpl(CreateQrcodeUseCase createQrcodeUseCase) {
        this.createQrcodeUseCase = createQrcodeUseCase;
    }


    @Override
    public void create(QrCode qrCode) {
        createQrcodeUseCase.run(qrCode);
    }
}
