package com.julioceno.qrcodeservice.core.application.service;

import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import com.julioceno.qrcodeservice.core.application.usecases.CreateQrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;

public class CreateQrcodeServiceImpl implements CreateQrcodeUseCase {
    private QrcodeProviderPort qrcodeProviderPort;

    public CreateQrcodeServiceImpl(QrcodeProviderPort qrcodeProviderPort) {
        this.qrcodeProviderPort = qrcodeProviderPort;
    }

    @Override
    public void run(QrCode qrCode) {
        byte[] qrcodePdfImage = qrcodeProviderPort.generatePng(qrCode.getUrl());
        IO.println(qrcodePdfImage.length);
    }
}
