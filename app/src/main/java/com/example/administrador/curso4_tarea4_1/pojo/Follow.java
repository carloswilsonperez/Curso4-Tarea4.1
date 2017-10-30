package com.example.administrador.curso4_tarea4_1.pojo;

/**
 * Created by administrador on 29/10/17.
 */

// Clase para recibir la respuesta al hacer click en seguir un usuario de instagram
public class Follow {

    private String estadoSalida;
    private String estadoEntrada;
    private String usuarioPrivado;


    public Follow(String estadoSalida, String usuarioPrivado) {
        this.estadoSalida = estadoSalida;
        this.usuarioPrivado = usuarioPrivado;
    }

    public Follow(String estadoSalida, String estadoEntrada, String usuarioPrivado) {
        this.estadoSalida = estadoSalida;
        this.estadoEntrada = estadoEntrada;
        this.usuarioPrivado = usuarioPrivado;
    }

    public String getEstadoSalida() {
        return estadoSalida;
    }

    public void setEstadoSalida(String estadoSalida) {
        this.estadoSalida = estadoSalida;
    }

    public String getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(String estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    public String getUsuarioPrivado() {
        return usuarioPrivado;
    }

    public void setUsuarioPrivado(String usuarioPrivado) {
        this.usuarioPrivado = usuarioPrivado;
    }
}
