package com.example.administrador.curso4_tarea4_1.servicios;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

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

    public final static String TAG = "NOTIFICATION_SERVICE";
    public final static int NOTIFICATION_ID = 128;
    public Activity activity;

    @Override //este método se ejecuta al recibir una notificación
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Tag From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Mensage Body: " + remoteMessage.getNotification().getBody());

        Intent intent = new Intent(this, MainActivity.class);
        //int notificacionId=128;

        intent.putExtra("numTab", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); //Intent especifico para notificaciones

        // Obtengo el sonido del teléfono configurado para notificaciones
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int color = getResources().getColor(R.color.colorAccent);

        //***** Notificacion con la acción de "Abrir mi perfil"
        Intent intentVerMiPerfil = new Intent();//Está vacío para que lo procese el Broadcast
        intentVerMiPerfil.setAction("VER_MI_PERFIL");
        PendingIntent pendingIntentVerMiPerfil = PendingIntent.getBroadcast(this, 0, intentVerMiPerfil, PendingIntent.FLAG_UPDATE_CURRENT);

        //***** Notificacion con la acción de "Follow/Ufollow", seguir o no seguir al usuario en instagram
        Intent intentSeguirUsuario = new Intent();//Está vacío para que lo procese el Broadcast
        intentSeguirUsuario.setAction("SEGUIR_USUARIO");
        PendingIntent pendingIntentSeguirUsuario = PendingIntent.getBroadcast(this, 0, intentSeguirUsuario, PendingIntent.FLAG_UPDATE_CURRENT);


        //***** Notificacion con la acción de "Ver usuario", nos lleva al activity en la que se visualizan las
        // fotos recientes del usuario que dió me gusta en la foto
        Intent intentVerUsuario = new Intent(); //Está vacío para que lo procese el Broadcast
        intentVerUsuario.setAction("VER_USUARIO");
        PendingIntent pendingIntentVerUsuario = PendingIntent.getBroadcast(this, 0, intentVerUsuario, PendingIntent.FLAG_UPDATE_CURRENT);

        // Objetos Accion para ser utilizada desde el objeto WearableExtender
        // Abrirá la pantalla de home del usuario establecido
        NotificationCompat.Action actionVerMiPerfil =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_notificacion_perro,
                        getString(R.string.texto_accion_ver_mi_perfil), pendingIntentVerMiPerfil)
                        .build();
        //Seguir/dejarDeSeguir al usuario que raiteo tu foto de instagram
        NotificationCompat.Action actionSeguirUsuario =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_notificacion_perro,
                        getString(R.string.texto_accion_follow_unfollow), pendingIntentSeguirUsuario)
                        .build();

        NotificationCompat.Action actionVerUsuario =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_notificacion_perro,
                        getString(R.string.texto_accion_ver_usuario), pendingIntentVerUsuario)
                        .build();

        // Objeto WearableExtender para usar y personalizar la notivicacion que solo será visible en el Wearable
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),
                        R.drawable.fondo_perro))
                .setGravity(Gravity.CENTER_VERTICAL)
                ;

        // ******* Crea la notificacion *********
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_shortcut_perro08)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setColor(color)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(actionVerMiPerfil))
                .extend(wearableExtender.addAction(actionSeguirUsuario))
                .extend(wearableExtender.addAction(actionVerUsuario))
     //           .addAction(R.drawable.ic_full_notificacion_perro, getString(R.string.texto_accion_ver_mi_perfil), pendingIntentVerMiPerfil)
     //           .addAction(R.drawable.ic_full_notificacion_perro, getString(R.string.texto_accion_follow_unfollow), pendingIntentSeguirUsuario)
     //           .addAction(R.drawable.ic_full_notificacion_perro, getString(R.string.texto_accion_ver_usuario), pendingIntentVerUsuario)
                ;


        // Clase para manejar las notificaciones, se usa la libreira android.support.v4.app.NotificationCompat
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());

    }

}
