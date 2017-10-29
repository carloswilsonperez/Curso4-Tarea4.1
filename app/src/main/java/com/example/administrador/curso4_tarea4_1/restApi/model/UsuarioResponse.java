package com.example.administrador.curso4_tarea4_1.restApi.model;

/**
 * Created by administrador on 03/08/17.
 */

public class UsuarioResponse {

    private String id;
    private String token;

    public UsuarioResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public UsuarioResponse() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
