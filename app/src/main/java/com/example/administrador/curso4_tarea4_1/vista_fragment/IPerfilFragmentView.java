package com.example.administrador.curso4_tarea4_1.vista_fragment;

import com.example.administrador.curso4_tarea4_1.adapter.PerfilAdapter;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.pojo.Perfil;

import java.util.ArrayList;

/**
 * Created by administrador on 25/06/17.
 */

public interface IPerfilFragmentView {

    public void generarGridLayout();

    public PerfilAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(PerfilAdapter adaptador);

    public void mostrarPerfil(ArrayList<Perfil> perfiles);
}
