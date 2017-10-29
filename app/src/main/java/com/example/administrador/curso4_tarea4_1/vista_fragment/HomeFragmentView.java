package com.example.administrador.curso4_tarea4_1.vista_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.adapter.MascotaAdaptador;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.presentador.HomeFragmentPresenter;
import com.example.administrador.curso4_tarea4_1.presentador.IHomeFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by administrador on 17/05/17.
 */

public class HomeFragmentView extends Fragment implements IHomeFragmentView {

    ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    public MascotaAdaptador adaptador;
    private IHomeFragmentPresenter presentador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    Con esta línea le asignamos la clase java al layout
        View v = inflater.inflate(R.layout.fragment_home , container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presentador = new HomeFragmentPresenter(this, getContext());
        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        // Instacia el linearLayoutManager que sirve para manejar la forma en que se ve la lista
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}
