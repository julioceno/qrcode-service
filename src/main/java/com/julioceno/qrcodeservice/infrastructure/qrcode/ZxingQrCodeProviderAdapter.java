package com.julioceno.qrcodeservice.infrastructure.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ZxingQrCodeProviderAdapter implements QrcodeProviderPort {

    private final static int QR_CODE_WIDTH = 200;
    private final static int QR_CODE_HEIGHT = 200;

    @Override
    public byte[] generatePng(String url) {
        try {
            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(
                    url,
                    BarcodeFormat.QR_CODE,
                    QR_CODE_WIDTH,
                    QR_CODE_HEIGHT
            );

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngQrCodeData = pngOutputStream.toByteArray();

            pngOutputStream.close();

            return pngQrCodeData;
        } catch (WriterException | IOException e) {
            // TODO: handle with exception
            throw new RuntimeException(e);
        }
    }
}
