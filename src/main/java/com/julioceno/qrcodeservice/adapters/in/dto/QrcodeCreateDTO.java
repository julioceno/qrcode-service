package com.julioceno.qrcodeservice.adapters.in.dto;
import jakarta.validation.constraints.NotBlank;

public record QrcodeCreateDTO(
        @NotBlank(message = "{validation.required}")
        String url
) {
}
