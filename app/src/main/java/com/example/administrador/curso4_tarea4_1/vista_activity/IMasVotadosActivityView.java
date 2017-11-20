package com.example.administrador.curso4_tarea4_1.vista_activity;

import com.example.administrador.curso4_tarea4_1.adapter.MascotaAdapter;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by administrador on 27/05/17.
 */

public interface IMasVotadosActivityView {

    public void generarLinearLayoutVertical();

    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdapter adaptador);
}
