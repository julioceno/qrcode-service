package com.julioceno.qrcodeservice.adapters.in.dto;
import jakarta.validation.constraints.NotBlank;

public record QrcodeCreateDTO(
        @NotBlank(message = "Required url")
        String url
) {
}
