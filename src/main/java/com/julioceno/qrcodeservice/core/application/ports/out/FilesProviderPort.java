package com.julioceno.qrcodeservice.core.application.ports.out;

public interface FilesProviderPort {
    String uploadFile(byte[] file, String fileName);
}
