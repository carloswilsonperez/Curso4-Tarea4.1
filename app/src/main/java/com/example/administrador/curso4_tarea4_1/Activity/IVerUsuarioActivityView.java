package com.example.administrador.curso4_tarea4_1.Activity;

import com.example.administrador.curso4_tarea4_1.adapter.PerfilAdaptador;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.pojo.Perfil;

import java.util.ArrayList;

/**
 * Created by administrador on 10/11/17.
 */

public interface IVerUsuarioActivityView {

    public void generarGridLayout();

    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(PerfilAdaptador adaptador);

    public void mostrarPerfil(ArrayList<Perfil> perfiles);
}
