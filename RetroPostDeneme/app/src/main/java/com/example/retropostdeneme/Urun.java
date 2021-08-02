package com.example.retropostdeneme;

public class Urun {
    public String _id;
    public String id;
    public int fiyat;
    public String urun_ismi;
    public String urun_sahibi_email;
    public String urun_sahibi_name;
    public String urun_sahibi_id;
    public String urun_siparis_eden_id;

    public String getUrun_siparis_eden_id() {
        return urun_siparis_eden_id;
    }

    public void setUrun_siparis_eden_id(String urun_siparis_eden_id) {
        this.urun_siparis_eden_id = urun_siparis_eden_id;
    }

    public String getUrun_sahibi_id() {
        return urun_sahibi_id;
    }

    public void setUrun_sahibi_id(String urun_sahibi_id) {
        this.urun_sahibi_id = urun_sahibi_id;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public String getUrun_ismi() {
        return urun_ismi;
    }

    public void setUrun_ismi(String urun_ismi) {
        this.urun_ismi = urun_ismi;
    }


    public String get_id() {
        return _id;
    }

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

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String encodedImage;


}
