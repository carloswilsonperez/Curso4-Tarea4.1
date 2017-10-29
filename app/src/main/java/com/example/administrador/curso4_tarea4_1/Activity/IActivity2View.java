package com.example.administrador.curso4_tarea4_1.Activity;

import com.example.administrador.curso4_tarea4_1.adapter.MascotaAdaptador;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by administrador on 27/05/17.
 */

public interface IActivity2View {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
