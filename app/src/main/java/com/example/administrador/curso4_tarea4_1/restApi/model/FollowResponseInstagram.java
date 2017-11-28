package com.example.administrador.curso4_tarea4_1.restApi.model;

/**
 * Created by administrador on 28/10/17.
 */

// Clase para manejar la respuesta de instagram al seguir un usuuario, dado que devuelve un array
public class FollowResponseInstagram {

    private String estadoEntrante;
    private String estadoSaliente;
    private String usuarioObjetivoPrivado;
    private static final String TAG = "FOLLOW_RESPONSE";

    //Log.d(TAG, "El valor de estadoEntrante es-> " + estadoEntrante);
    public FollowResponseInstagram() {
    }

    public FollowResponseInstagram(String estadoEntrante) {
        this.estadoEntrante = estadoEntrante;
    }

    public FollowResponseInstagram(String estadoEntrante, String usuarioObjetivoPrivado) {
        this.estadoEntrante = estadoEntrante;
        this.usuarioObjetivoPrivado = usuarioObjetivoPrivado;
    }

    public FollowResponseInstagram(String estadoEntrante, String estadoSaliente, String usuarioObjetivoPrivado) {
        this.estadoEntrante = estadoEntrante;
        this.estadoSaliente = estadoSaliente;
        this.usuarioObjetivoPrivado = usuarioObjetivoPrivado;
    }

    public String getEstadoEntrante() {
        return estadoEntrante;
    }

    public void setEstadoEntrante(String estadoEntrante) {
        this.estadoEntrante = estadoEntrante;
    }

    public String getEstadoSaliente() {
        return estadoSaliente;
    }

    public void setEstadoSaliente(String estadoSaliente) {
        this.estadoSaliente = estadoSaliente;
    }

    public String getUsuarioObjetivoPrivado() {
        return usuarioObjetivoPrivado;
    }

    public void setUsuarioObjetivoPrivado(String usuarioObjetivoPrivado) {
        this.usuarioObjetivoPrivado = usuarioObjetivoPrivado;
    }
}
