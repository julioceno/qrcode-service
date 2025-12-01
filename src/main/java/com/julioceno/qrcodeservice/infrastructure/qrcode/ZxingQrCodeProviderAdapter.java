package com.julioceno.qrcodeservice.infrastructure.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.julioceno.qrcodeservice.core.application.exception.QrCodeGenerationException;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerFactoryPort;
import com.julioceno.qrcodeservice.core.application.ports.out.LoggerPort;
import com.julioceno.qrcodeservice.core.application.ports.out.QrcodeProviderPort;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ZxingQrCodeProviderAdapter implements QrcodeProviderPort {
    private final static int QR_CODE_WIDTH = 200;
    private final static int QR_CODE_HEIGHT = 200;

    private final LoggerPort logger;

    public ZxingQrCodeProviderAdapter(LoggerFactoryPort LoggerFactoryPort) {
        this.logger = LoggerFactoryPort.getLogger(ZxingQrCodeProviderAdapter.class);
    }

    @Override
    public byte[] generatePng(String url) {
        logger.info("Starting QR code generation for URL: " + url);
        try {
            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(
                    url,
                    BarcodeFormat.QR_CODE,
                    QR_CODE_WIDTH,
                    QR_CODE_HEIGHT
            );

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
                byte[] qrCodeBytes = outputStream.toByteArray();

                logger.info("QR Code successfully generated for URL: " + url);

                return qrCodeBytes;
            }
        } catch (WriterException | IOException e) {
            logger.error("Failed to generate QR code for URL: {} ", url,  e);

            throw new QrCodeGenerationException("Failed to generate QR code", e);
        }
    }
}
