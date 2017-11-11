package com.example.administrador.curso4_tarea4_1.presentador;

import android.content.Context;

import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.pojo.Perfil;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.vista_fragment.IPerfilFragmentView;

import java.util.ArrayList;

/**
 * Created by administrador on 10/11/17.
 */

public class ActivityVerUsuarioPresenter implements IActivityVerUsuarioPresenter{

    private static final String TAG = "PerfilFragmentPresenter";
    private Context context;
    private IPerfilFragmentView iPerfilFragmentView;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Perfil> perfiles;
    DatosPreferencias datosPreferencias;

    @Override
    public void obtenerMediosRecientes() {

    }

    @Override
    public void mostrarMediosRecientesRv() {

    }

    @Override
    public void obtenerPerfil() {

    }

    @Override
    public void obtenerIdPerfil(String usuario) {

    }

    @Override
    public void mostrarPerfil() {

    }
}
