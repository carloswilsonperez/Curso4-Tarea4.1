package com.example.administrador.curso4_tarea4_1.restApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.administrador.curso4_tarea4_1.pojo.Follow;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.FollowResponseInstagram;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrador on 28/10/17.
 */

public class SeguirUsuario extends BroadcastReceiver {

    DatosPreferencias datosPreferencias;
    Context context;
    private ArrayList<Follow> follows;
    private static final String TAG = "SeguirUsuario";

    @Override
    public void onReceive(Context context, Intent intent) {
        String KEY_ACTION_FOLLOW = "SEGUIR_USUARIO";
        String accionIntent = intent.getAction();
        if (KEY_ACTION_FOLLOW.equals(accionIntent)){
            //Toast.makeText(context, "Activaste seguir al usuario", Toast.LENGTH_SHORT).show();
            String idUsuario = "5557461888";
            String token = "5557323253.5477f1a.a6c8d1cf0f9747fe91b9c884bc63fcc4";
            String accion = "follow";
            followUnfollow(idUsuario, token, accion);
        }
    }

    //*** Método para seguir a un usuario de instagram  ****
    private void followUnfollow (String idUsuarioInstagram, String tokenInstagram, String accion){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); // Instancia un objeto restApiAdapter
        Gson gsonFolow = restApiAdapter.construyeGesonDeserializadorFollow();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFolow); // Crea la endpoint con la conexion a usar
        Call<FollowResponseInstagram> followResponseInstagramCall = endpointsApi.seguirUsuarioInstagram(idUsuarioInstagram,tokenInstagram, accion);

        followResponseInstagramCall.enqueue(new Callback<FollowResponseInstagram>() {
            @Override
            public void onResponse(Call<FollowResponseInstagram> call, Response<FollowResponseInstagram> response) {
                String hola = "hola";
                FollowResponseInstagram followResponseInstagram = response.body();
             /*   follows = followResponseInstagram.getFollows();
                String salida= follows.get(0).getEstadoSalida();
                Toast.makeText(context, salida, Toast.LENGTH_SHORT).show();  */
            }

            @Override
            public void onFailure(Call<FollowResponseInstagram> call, Throwable t) {
//                Toast.makeText(context, "¡Algo pasó al dar seguir al usuario! Verifica la conexión", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });
    }

}
