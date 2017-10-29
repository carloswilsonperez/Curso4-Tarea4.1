package com.example.administrador.curso4_tarea4_1.pojo;

/**
 * Created by administrador on 02/07/17.
 */

public class Perfil {

    private String idUsuario;
    private String nombreUsuario;
    private String nombreCompleto;
    private String urlFotoPerfil;

    public Perfil(){

    }

    public Perfil(String idUsuario, String nombreUsuario, String nombreCompleto, String urlFotoPerfil) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }
}
