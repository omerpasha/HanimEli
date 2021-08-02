package com.example.retropostdeneme;

public class PostsSiparisAlinanlar {
    public String body;
    public Urun [] urunler;
    public int fiyat;
    public String id;
    public String encodedImage;
    public String urun_ismi;
    public String urun_id;
    public String urun_sahibi_isim;
    public String urun_sahibi_mail;
    public String urun_sahibi_id;

    public String getUrun_sahibi_id() {
        return urun_sahibi_id;
    }

    public void setUrun_sahibi_id(String urun_sahibi_id) {
        this.urun_sahibi_id = urun_sahibi_id;
    }



    public String getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(String urun_id) {
        this.urun_id = urun_id;
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
    @Override
    public String toString() {
        return "Posts{" +
                ", urunler=" + urunler +
                '\'' +fiyat+'\''
                +id+'\''+
                '\''+
                encodedImage+
                '\''+urun_ismi+
                '\''+urun_sahibi_isim+
                '\''+urun_sahibi_id+
                '\''+urun_sahibi_mail+
                '\''+urun_id+
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
