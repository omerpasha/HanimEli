package com.example.retropostdeneme;

public class PostsMagazaciProfil {

    private String body;

    private String urun_ismi;//ürünün ismi
    private String urun_sahibi_name;//ürünün ismi
    private String urun_sahibi_email;//ürünün ismi
    private String urun_sahibi_id;
    private int fiyat;//ürünün fiyatı

    public String getUrun_sahibi_email() {
        return urun_sahibi_email;
    }

    public void setUrun_sahibi_email(String urun_sahibi_email) {
        this.urun_sahibi_email = urun_sahibi_email;
    }

    public String getUrun_sahibi_name() {
        return urun_sahibi_name;
    }

    public void setUrun_sahibi_name(String urun_sahibi_name) {
        this.urun_sahibi_name = urun_sahibi_name;
    }



    public String getUrun_ismi() {
        return urun_ismi;
    }

    public void setUrun_ismi(String urun_ismi) {
        this.urun_ismi = urun_ismi;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }


    public String getUrun_sahibi_id() {
        return urun_sahibi_id;
    }

    public void setUrun_sahibi_id(String urun_sahibi_id) {
        this.urun_sahibi_id = urun_sahibi_id;
    }

    private String encodedImage;

    public String getBody() {
        return body;
    }




    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "Posts{" +
                ", urun_sahibi_id=" + urun_sahibi_id +
                ", encodedImage='" + encodedImage + '\'' +
                ", urun ismi='" + urun_ismi + '\'' +
                ", fiyat='" + fiyat + '\'' +
                ", urun sahibi isim='" + urun_sahibi_name + '\'' +
                ", urun sahibi email='" + urun_sahibi_email + '\'' +
                ", fiyat='" + fiyat + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
