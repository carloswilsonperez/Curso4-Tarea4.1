package com.example.administrador.curso4_tarea4_1.servicios;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by administrador on 05/08/17.
 */
// Esta clase maneja el servicio de token
public class NotificationIDTokenService extends FirebaseInstanceIdService {

    public final static String TAG = "FIREBASE_TOKEN";

    @Override //este método esta escuchando para recibir el token o si el token cambia
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken(); // Obtiene el token
        enviarTokenRegistro(token);
    }

    // Metodo para enviar el tokens
    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
