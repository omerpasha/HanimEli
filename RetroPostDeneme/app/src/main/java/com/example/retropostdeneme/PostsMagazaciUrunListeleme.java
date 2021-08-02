package com.example.retropostdeneme;
public class PostsMagazaciUrunListeleme{

    public String body;
    public Urun [] urunler;

    public String getUrun_sahibi_id() {
        return urun_sahibi_id;
    }

    public void setUrun_sahibi_id(String urun_sahibi_id) {
        this.urun_sahibi_id = urun_sahibi_id;
    }

    public Urun []fiyat;
    public String urun_sahibi_id;

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


    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "Posts{" +
                ", urunler=" + urunler +
                ",urun_sahibi_id="+urun_sahibi_id+'\'' +
                 '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
