package com.example.retropostdeneme;

public class PostsMagazaciListedenUrunSil {

    private String body;
    private  String id;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "Posts{" +
                ",id="+id+'\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
