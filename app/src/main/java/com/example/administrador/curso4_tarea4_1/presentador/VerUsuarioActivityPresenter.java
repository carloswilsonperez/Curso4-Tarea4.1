package com.example.administrador.curso4_tarea4_1.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrador.curso4_tarea4_1.Activity.IVerUsuarioActivityView;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.pojo.Perfil;
import com.example.administrador.curso4_tarea4_1.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.PerfilResponse;
import com.example.administrador.curso4_tarea4_1.vista_fragment.IPerfilFragmentView;
import java.util.ArrayList;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;import java.util.ArrayList;
import retrofit2.Response;
/**
 * Created by administrador on 10/11/17.
 */

public class VerUsuarioActivityPresenter implements IVerUsuarioActivityPresenter {

    private static final String TAG = "VerUsuarioPresenter";
    private Context context;
    private IVerUsuarioActivityView iVerUsuarioActivityView;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Perfil> perfiles;
    DatosPreferencias datosPreferencias;

    public VerUsuarioActivityPresenter(IVerUsuarioActivityView iVerUsuarioActivityView, Context context) {
        this.iVerUsuarioActivityView = iVerUsuarioActivityView;
        this.context = context;
        datosPreferencias = new DatosPreferencias(context); // carga las preferencias
        obtenerPerfil();
        obtenerMediosRecientes();
    }

    public VerUsuarioActivityPresenter() {
    }

    @Override
    public void obtenerMediosRecientes() {

    }

    @Override
    public void mostrarMediosRecientesRv() {

    }

    @Override   // Llamdo para obtener el perfil de la api de instagram
    public void obtenerPerfil() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonPerfil = restApiAdapter.construyeGesonDeserializadorPerfil();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonPerfil);
        String usuario = datosPreferencias.getUsuarioApi();
        Call<PerfilResponse> perfilResponseCall = endpointsApi.getPerfil(usuario, ConstantesRestApi.ACCESS_TOKEN);
        perfilResponseCall.enqueue(new Callback<PerfilResponse>() {
            @Override
            public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                PerfilResponse perfilResponse = response.body(); //obtiene solo la data del objeto json recibido
                perfiles = perfilResponse.getPerfil();// guarda el ArrayList con el perfil
                mostrarPerfil();
            }

            public void onFailure(Call<PerfilResponse> call, Throwable t) {
                Toast.makeText(context, "¡Algo pasó al obtener el perfil! Verifica la conexión", Toast.LENGTH_LONG).show();//Mensaje para el usuarioApi
                Log.e(TAG, t.toString());
            }
        });


    }

    @Override
    public void obtenerIdPerfil(String usuario) {

    }

    @Override
    public void mostrarPerfil() {

    }
}
