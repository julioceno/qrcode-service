package com.julioceno.qrcodeservice.adapters.out.entities;


import com.julioceno.qrcodeservice.core.domain.QrCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QrCodeEntity {
    @Id
    private String id;
    private String url;
    private String qrcodeUrl;

    public QrCodeEntity(QrCode qrCode) {
        this.id = qrCode.getId();
        this.url = qrCode.getUrl();
        this.qrcodeUrl = qrCode.getQrcodeUrl();
    }
}
