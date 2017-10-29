package com.example.administrador.curso4_tarea4_1.restApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;

/**
 * Created by administrador on 28/10/17.
 */

public class SeguirUsuario extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String KEY_ACTION_FOLLOW = "SEGUIR_USUARIO";
        String accionIntent = intent.getAction();
        if (KEY_ACTION_FOLLOW.equals(accionIntent)){
            Toast.makeText(context, "Activaste seguir al usuario", Toast.LENGTH_SHORT).show();
        }
    }

    //*** Método para seguir a un usuario de instagram  ****
    private void followUnfollow (String idUsuarioInstagram, String tokenInstagram, String accion){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); // Instancia un objeto restApiAdapter
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram2(); // Crea la endpoint con la conexion a usar
        endpointsApi.seguirUsuarioInstagram(idUsuarioInstagram,tokenInstagram, accion);
    }

}
