package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;

public class CreateQrcodeServiceImpl implements CreateQrcodeUseCase {

    public CreateQrcodeServiceImpl() {
    }

    @Override
    public void run(QrCode qrCode) {
        IO.println("po");
    }
}
