package com.example.administrador.curso4_tarea4_1.restApi;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by administrador on 28/06/17.
 */

public class DatosPreferencias {

    private static String usuarioApi;
    private static String idUsuarioApi;
    private Context context;
    SharedPreferences miPreferencia;

    public DatosPreferencias(Context context) {
        this.context = context;
        miPreferencia = context.getSharedPreferences(ConstantesRestApi.MI_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getUsuarioApi() { // obtiene el nombre de usuario instagram, y si no existe coloca el usuario por default "supermascota5"
        usuarioApi = miPreferencia.getString(ConstantesRestApi.key_USUARIO_API, "");
        return usuarioApi;
    }

    public void setUsuarioApi(String usuarioApi) {
        this.usuarioApi = usuarioApi;
        SharedPreferences.Editor editor = miPreferencia.edit();
        editor.putString(ConstantesRestApi.key_USUARIO_API, usuarioApi);
        editor.commit();
    }

    public String getIdUsuarioApi() { // obtiene el id de usuario instagram, y si no existe coloca el id del usuario por default "supermascota5"
        idUsuarioApi = miPreferencia.getString(ConstantesRestApi.key_ID_USUARIO_API, "");
        return idUsuarioApi;
    }

    public void setIdUsuarioApi(String idUsuarioApi) {

        this.idUsuarioApi = idUsuarioApi;
        SharedPreferences.Editor editor = miPreferencia.edit();
        editor.putString(ConstantesRestApi.key_ID_USUARIO_API, idUsuarioApi);
        editor.commit();
    }

}
