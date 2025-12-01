package com.julioceno.qrcodeservice.core.application.ports.out;

public interface QrcodeProviderPort {
    byte[] generatePng(String url);
}
