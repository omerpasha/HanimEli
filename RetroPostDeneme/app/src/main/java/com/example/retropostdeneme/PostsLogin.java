package com.example.retropostdeneme;

public class PostsLogin {
    private String body;

    private String name;
    private String email;
    private  String id;
    private String password;
    private String rol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
                "name=" + name +
                ", rol=" + rol +'\'' +
                ",id="+id+'\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
