package com.julioceno.qrcodeservice.core.domain;

public class QrCode {
    private String id;
    private String url;
    private String qrcodeUrl;

    public QrCode() {
    }

    public QrCode(String id, String url, String qrcodeUrl) {
        this.id = id;
        this.url = url;
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }
}
