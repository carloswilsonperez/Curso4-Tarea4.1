package com.example.administrador.curso4_tarea4_1.servicios;


import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;

import com.example.administrador.curso4_tarea4_1.Activity.AcercadeActivity;
import com.example.administrador.curso4_tarea4_1.Activity.MainActivity;
import com.example.administrador.curso4_tarea4_1.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by administrador on 05/08/17.
 */
// Clase que maneja el serivcio de Notificaciones
public class NotificationService extends FirebaseMessagingService {

    public final static String TAG = "FIREBASE";
    public final static int NOTIFICATION_ID = 128;

    @Override //este método se ejecuta al recibir una notificación
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Tag From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Mensage Body: " + remoteMessage.getNotification().getBody());

        Intent intent = new Intent(this, MainActivity.class);
        int numTab = 1;
        //int notificacionId=128;

        intent.putExtra("numTab", numTab);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); //Intent especifico para notificaciones

        // Obtengo el sonido del teléfono configurado para notificaciones
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int color = getResources().getColor(R.color.colorAccent);

        //***** Notificacion con la acción de "Abrir mi perfil"
        Intent intentVerMiPerfil = new Intent(this, AcercadeActivity.class);
        PendingIntent pendingIntentVerMiPerfil = PendingIntent.getActivity(this, 0, intentVerMiPerfil, PendingIntent.FLAG_UPDATE_CURRENT);

        //***** Notificacion con la acción de "Follow/Ufollow", seguir o no seguir al usuario en instagram
        Intent intentSeguirUsuario = new Intent();
        intentSeguirUsuario.setAction("SEGUIR_USUARIO");
        PendingIntent pendingIntentSeguirUsuario = PendingIntent.getBroadcast(this, 0, intentSeguirUsuario, PendingIntent.FLAG_UPDATE_CURRENT);


        //***** Notificacion con la acción de "Ver usuario", nos lleva al activity en la que se visualizan las
        // fotos recientes del usuario que dió me gusta en la foto
        Intent intentVerUsuario = new Intent(this, AcercadeActivity.class);
        PendingIntent pendingIntentVerUsuario = PendingIntent.getActivity(this, 0, intentVerUsuario, PendingIntent.FLAG_UPDATE_CURRENT);


        // ******* Crea la notificacion *********
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setColor(color)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_full_notificacion_perro, getString(R.string.texto_accion_ver_mi_perfil), pendingIntentVerMiPerfil)
                .addAction(R.drawable.ic_full_notificacion_perro, getString(R.string.texto_accion_follow_unfollow), pendingIntentSeguirUsuario)
                .addAction(R.drawable.ic_full_notificacion_perro, getString(R.string.texto_accion_ver_usuario), pendingIntentVerUsuario)
                ;


        // Clase para manejar las notificaciones, se usa la libreira android.support.v4.app.NotificationCompat
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());

    }

}
