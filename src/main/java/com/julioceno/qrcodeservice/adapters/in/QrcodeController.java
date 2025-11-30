package com.julioceno.qrcodeservice.adapters.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/qr")
public class QrcodeController {

    @PostMapping
    public ResponseEntity create() {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand("") // TODO: use correct ID
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
