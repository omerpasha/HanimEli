package com.example.retropostdeneme;

public class PostsMusteriSiparisler {
    public String body;
    public Urun [] urunler;
    public Urun []fiyat;
    public String urun_id;
    public Urun[] getFiyat() {
        return fiyat;
    }

    public void setFiyat(Urun[] fiyat) {
        this.fiyat = fiyat;
    }



    public Urun[] getUrunler() {
        return urunler;
    }

    public String getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(String urun_id) {
        this.urun_id = urun_id;
    }

    public void setUrunler(Urun[] urunler) {
        this.urunler = urunler;
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
                '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
