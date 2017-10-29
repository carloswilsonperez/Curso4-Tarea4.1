package com.example.administrador.curso4_tarea4_1.restApi.model;

import com.example.administrador.curso4_tarea4_1.pojo.Perfil;

import java.util.ArrayList;

/**
 * Created by administrador on 02/07/17.
 */

public class PerfilResponse {

    ArrayList<Perfil> perfiles;

    public ArrayList<Perfil> getPerfil() {
        return perfiles;
    }

    public void setPerfil(ArrayList<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}
