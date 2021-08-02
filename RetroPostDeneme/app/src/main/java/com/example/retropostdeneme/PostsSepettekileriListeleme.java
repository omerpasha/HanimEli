package com.example.retropostdeneme;

public class PostsSepettekileriListeleme {

    public String body;
    public Urun [] urunler;
    public Urun []fiyat;
    public String id;

    public Urun[] getFiyat() {
        return fiyat;
    }

    public void setFiyat(Urun[] fiyat) {
        this.fiyat = fiyat;
    }

    public Urun[] getUrunler() {
        return urunler;
    }

    public void setUrunler(Urun[] urunler) {
        this.urunler = urunler;
    }

    public String getBody() {
        return body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "Posts{" +
                ", urunler=" + urunler +
                ", id=" + id +
                '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
