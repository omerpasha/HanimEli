package com.example.retropostdeneme;

public class PostsSepeteEkleme {
    public String body;
    public Urun [] urunler;
    public int fiyat;
    public String urun_sahibi_id;
    public String urun_siparis_eden_id;
    public String encodedImage;
    public String urun_ismi;

    public String getUrun_siparis_eden_id() {
        return urun_siparis_eden_id;
    }

    public void setUrun_siparis_eden_id(String urun_siparis_eden_id) {
        this.urun_siparis_eden_id = urun_siparis_eden_id;
    }

    public String getUrun_sahibi_isim() {
        return urun_sahibi_isim;
    }

    public void setUrun_sahibi_isim(String urun_sahibi_isim) {
        this.urun_sahibi_isim = urun_sahibi_isim;
    }

    public String getUrun_sahibi_mail() {
        return urun_sahibi_mail;
    }

    public void setUrun_sahibi_mail(String urun_sahibi_mail) {
        this.urun_sahibi_mail = urun_sahibi_mail;
    }

    public String urun_sahibi_isim;
    public String urun_sahibi_mail;

    public String getUrun_ismi() {
        return urun_ismi;
    }

    public void setUrun_ismi(String urun_ismi) {
        this.urun_ismi = urun_ismi;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrun_sahibi_id() {
        return urun_sahibi_id;
    }

    public void setUrun_sahibi_id(String urun_sahibi_id) {
        this.urun_sahibi_id = urun_sahibi_id;
    }

    @Override
    public String toString() {
        return "Posts{" +
                ", urunler=" + urunler +
                '\'' +fiyat+'\''
                +urun_sahibi_id+'\''+
                '\''+
                encodedImage+
                '\''+urun_ismi+
                '\''+urun_siparis_eden_id+
                '\''+urun_sahibi_isim+
                '\''+urun_sahibi_mail+
                ", body='" + body + '\'' +
                '}';
    }

    public Urun[] getUrunler() {
        return urunler;
    }

    public void setUrunler(Urun[] urunler) {
        this.urunler = urunler;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }



}
