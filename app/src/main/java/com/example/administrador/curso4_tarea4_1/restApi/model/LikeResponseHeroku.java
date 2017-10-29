package com.example.administrador.curso4_tarea4_1.restApi.model;

/**
 * Created by administrador on 18/08/17.
 */

public class LikeResponseHeroku {

    private String id_like;
    private String id_foto_instagram;
    private String id_usuario_instagram;
    private String id_dispositivo;

    public LikeResponseHeroku(String id_like, String id_foto_instagram, String id_usuario_instagram, String id_dispositivo) {
        this.id_like = id_like;
        this.id_foto_instagram = id_foto_instagram;
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_dispositivo = id_dispositivo;
    }

    public LikeResponseHeroku(String id_like) {
        this.id_like = id_like;
    }

    public String getId_like() {
        return id_like;
    }

    public void setId_like(String id_like) {
        this.id_like = id_like;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
}
