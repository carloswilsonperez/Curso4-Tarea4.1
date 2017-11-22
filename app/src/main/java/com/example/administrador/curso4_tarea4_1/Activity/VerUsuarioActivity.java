package com.example.administrador.curso4_tarea4_1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.adapter.MediaUsuarioAdapter;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "VerUsuarioActivity";
    RecyclerView rvMediaUsuario;
    ArrayList<Mascota> mascotas;
    MediaUsuarioAdapter mediaUsuarioAdapter;
    DatosPreferencias datosPreferencias;
    String idUsuarioInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        datosPreferencias = new DatosPreferencias(this); // carga las preferencias
        idUsuarioInstagram = datosPreferencias.getIdUsuarioApi();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el titulo del ToolBar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // Activia boton para atras
        }
        /* Inicia el ReciclerView*/
        rvMediaUsuario = (RecyclerView)findViewById(R.id.rvMediaUsuario);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
        rvMediaUsuario.setLayoutManager(linearLayoutManager);
        obtenerMediosRecientes();
    }

    public void mostrarMediosRecientesRv(){
        mediaUsuarioAdapter = new MediaUsuarioAdapter(mascotas, this);
        rvMediaUsuario.setAdapter(mediaUsuarioAdapter);
    }


    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        String token = ConstantesRestApi.ACCESS_TOKEN;
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia(idUsuarioInstagram, token);  // El metodo getRecentMedia realiza la petición y lo guarda en el objeto Call de la clase Retrofit
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() { //Metodo para controlar el resultado de la respuesta, si trae datos o no
            @Override // Si la conexión es exitosa:
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body(); //obtiene solo la data del objeto json recibido
                mascotas = mascotaResponse.getMascotas();// guarda el ArrayList de mascotas
                String stop = "stop";
                mostrarMediosRecientesRv();
            }
            @Override // Si la conexión falla:
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Log.e(TAG, "¡Algo pasó en la descarga de medios recientes! Verifica la conexión");
                Log.e(TAG, t.toString());
            }
        });
    }


}
