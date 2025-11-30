package com.julioceno.qrcodeservice.adapters.in.controllers;

import com.julioceno.qrcodeservice.adapters.in.dto.QrcodeCreateDTO;
import com.julioceno.qrcodeservice.core.application.dto.CreateQRcodeDTO;
import com.julioceno.qrcodeservice.core.application.usecases.QrcodeUseCase;
import com.julioceno.qrcodeservice.core.domain.QrCode;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/qr")
@AllArgsConstructor
public class QrcodeController {
    private final QrcodeUseCase qrcodeUseCase;

    @PostMapping
    public ResponseEntity create(
            @RequestBody @Valid QrcodeCreateDTO dto
    ) {
        QrCode qrCode = new QrCode(
                null,
                dto.url(),
                dto.url()
        );

        qrcodeUseCase.create(qrCode);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand("") // TODO: use correct ID
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
