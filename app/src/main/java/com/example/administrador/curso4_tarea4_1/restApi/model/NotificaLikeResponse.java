package com.example.administrador.curso4_tarea4_1.restApi.model;

/**
 * Created by administrador on 21/08/17.
 */

public class NotificaLikeResponse {

    String id_dispositivo;

    public NotificaLikeResponse(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
}
