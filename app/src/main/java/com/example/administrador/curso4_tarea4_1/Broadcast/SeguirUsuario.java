package com.example.administrador.curso4_tarea4_1.Broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.administrador.curso4_tarea4_1.Activity.MainActivity;
import com.example.administrador.curso4_tarea4_1.Activity.VerUsuarioActivity;
import com.example.administrador.curso4_tarea4_1.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.FollowResponseInstagram;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrador on 28/10/17.
 */

public class SeguirUsuario extends BroadcastReceiver {

    DatosPreferencias datosPreferencias;
    Handler mhandler=new Handler();
    String estadoSaliente;
    String estadoEntrante;
    String usuarioObjetivoPrivado;
    private static final String TAG = "SeguirUsuario";
    public String idUsuarioInstagram;
    public String usuarioInstagram = "Juan";
    public String tokenInstagram = ConstantesRestApi.ACCESS_TOKEN;
    public String accion;
    public Context miContexto;

    @Override
    public void onReceive(Context context, Intent intent) {
        String KEY_ACTION_FOLLOW = "SEGUIR_USUARIO";
        String KEY_ACTION_VER_USUARIO = "VER_USUARIO"; // Abre el activity VerUsuario
        String KEY_ACTION_VER_MI_PERFIL = "VER_MI_PERFIL"; //Abre el fragment Home del MainActivity
        String accionIntent = intent.getAction();
        miContexto = context;
//        idUsuarioInstagram = datosPreferencias.getIdUsuarioApi();
//        usuarioInstagram = datosPreferencias.getUsuarioApi();

        //****** Seguir o dejar de seguir a un usuario en instagram *****************
        if (KEY_ACTION_FOLLOW.equals(accionIntent)){  // Chequea el codigo de la acción recibida
            //Toast.makeText(context, "Activaste seguir al usuario", Toast.LENGTH_SHORT).show();
            checkStatusFollow(idUsuarioInstagram, tokenInstagram);  // Consulta si se está siguiendo a ese usuario
            mhandler.postDelayed(new Runnable() { // Espera 1 segundo para recibir el estado y actuar en función
                @Override
                public void run() {
                    if (estadoSaliente.equalsIgnoreCase("follows")){ //Si se lo está siguiendo, se lo deja de seguir
                        accion = "unfollow";
                        followUnfollow(idUsuarioInstagram, tokenInstagram, accion);//Se deja de seguir al usuario de instagram
                        Toast toast1 = Toast.makeText( miContexto ,"Se ha dejado de seguir al usuario " + usuarioInstagram,
                                Toast.LENGTH_LONG);
                        toast1.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                        toast1.show();
                    }
                    else if (estadoSaliente.equalsIgnoreCase("none")){//Si no lo está siguiendo
                        accion = "follow";
                        followUnfollow(idUsuarioInstagram, tokenInstagram, accion);//Comienza a seguir al usuario de instagram
                        Toast toast2 = Toast.makeText( miContexto ,"Se ha comensado a seguir al usuario " + usuarioInstagram,
                                Toast.LENGTH_LONG);
                        toast2.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                        toast2.show();
                    }else {
                        Toast toast3 = Toast.makeText( miContexto ,"Huvo un error al intentar seguir al usuario " + usuarioInstagram,
                                Toast.LENGTH_LONG);
                        toast3.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                        toast3.show();
                    }
                }
            },1800);
            //Abre mi perfil
        }else if (KEY_ACTION_VER_MI_PERFIL.equals(accionIntent)){
            Intent intentAbrirHome = new Intent(miContexto, MainActivity.class);
            intentAbrirHome.putExtra("numTab", 2);
            /*Llamar a startActivity () desde fuera del contexto de una actividad requiere el indicador
            * Luego utilizando el contexto recibido llamo al método startActivity()*/
            intentAbrirHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            miContexto.startActivity(intentAbrirHome);

        }else if (KEY_ACTION_VER_USUARIO.equals(accionIntent)){
            Intent intentAbrirVerUsuario = new Intent(miContexto, VerUsuarioActivity.class);
            /*Llamar a startActivity () desde fuera del contexto de una actividad requiere el indicador
            * Luego utilizando el contexto recibido llamo al método startActivity()*/
            intentAbrirVerUsuario.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            miContexto.startActivity(intentAbrirVerUsuario);
        }
    }


    //*** Método para chequear si se está seguiendo a un usuario de instagram  ****
    private String checkStatusFollow (String idUsuarioInstagram, String tokenInstagram){
     //   public String estadoSaliente;
        RestApiAdapter restApiAdapter = new RestApiAdapter(); // Instancia un objeto restApiAdapter
        Gson gsonFollow = restApiAdapter.construyeGesonDeserializadorFollow();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollow); // Crea la endpoint con la conexion a usar
        Call<FollowResponseInstagram> followResponseInstagramCall = endpointsApi.querySeguirUsuarioInstagram(idUsuarioInstagram, tokenInstagram);
        followResponseInstagramCall.enqueue(new Callback<FollowResponseInstagram>() {
            @Override
            public void onResponse(Call<FollowResponseInstagram> call, Response<FollowResponseInstagram> response) {
                FollowResponseInstagram followResponseInstagram = response.body();
                if (followResponseInstagram != null){
                    estadoSaliente = followResponseInstagram.getEstadoSaliente();
                    estadoEntrante = followResponseInstagram.getEstadoEntrante();
                    usuarioObjetivoPrivado = followResponseInstagram.getUsuarioObjetivoPrivado();
                    Log.d(TAG, "CheckStatusFollow está funcionando OK");
                }
            }
            @Override
            public void onFailure(Call<FollowResponseInstagram> call, Throwable t) {//
                Log.e(TAG, t.toString());
                Log.d(TAG, "Error en el método CheckStatusFollow");
            }
        });

        return estadoSaliente;
    }

    //*** Método para seguir a un usuario de instagram  ****
    private void followUnfollow (String idUsuarioInstagram, String tokenInstagram, String accion){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); // Instancia un objeto restApiAdapter
        Gson gsonFollow = restApiAdapter.construyeGesonDeserializadorFollow();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollow); // Crea la endpoint con la conexion a usar
        Call<FollowResponseInstagram> followResponseInstagramCall = endpointsApi.seguirUsuarioInstagram(idUsuarioInstagram,tokenInstagram, accion);
        followResponseInstagramCall.enqueue(new Callback<FollowResponseInstagram>() {
            @Override
            public void onResponse(Call<FollowResponseInstagram> call, Response<FollowResponseInstagram> response) {
                FollowResponseInstagram followResponseInstagram = response.body();
                if (followResponseInstagram != null){
                    Log.d(TAG, "¡El estado de seguimiento a cambiado! OK");
                //    Toast.makeText(getApplitacionContext() , "Has comensado a seguir a " + followResponseInstagram.getEstadoSaliente(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FollowResponseInstagram> call, Throwable t) {
//                Toast.makeText(context, "¡Algo pasó al dar seguir al usuario! Verifica la conexión", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
                Log.d(TAG, "Error en el método followUnfollow");
            }
        });
    }

}
