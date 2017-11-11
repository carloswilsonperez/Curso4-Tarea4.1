package com.example.administrador.curso4_tarea4_1.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.LikeResponseHeroku;
import com.example.administrador.curso4_tarea4_1.restApi.model.LikeResponseInstagram;
import com.example.administrador.curso4_tarea4_1.restApi.model.NotificaLikeResponse;
import com.example.administrador.curso4_tarea4_1.restApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrador on 18/05/17.
 */

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;
    DatosPreferencias datosPreferencias;
    private static final String TAG = "PerfilAdaptador";


    //******** Constructor *******
    public PerfilAdaptador (ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // Método que va a inflar el layout y lo pasara al ViewHolder para que obtenga los views
    @Override
    public PerfilAdaptador.PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new PerfilAdaptador.PerfilViewHolder(v);
    }


    // Se setean los datos de la clase MascotaViewHolder con los datos de la lista recibida
    @Override
    public void onBindViewHolder(final PerfilAdaptador.PerfilViewHolder mascotaViewHolder, int position){
        final Mascota mascota = mascotas.get(position); //Obtiene todos los datos de la mascota en la posición position
        String ruta = mascota.getUrlFoto();
        final String idFotoInstagram = mascota.getIdFoto();
        ruta = ruta.replaceAll("\"", ""); //Quito las comillas dobles que vienen con la url desde el json
        Picasso.with(activity) // Libreria para traer las fotos
                .load(ruta) // trae la foto del usuarioApi
                .into(mascotaViewHolder.imgFoto); // ImagenView dode se va a mostrar la foto
        mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getLikes()));// Seteo el Número de likes del cardView
        mascotaViewHolder.llCardViewPerfil.setBackgroundResource(mascota.getColorFondo()); // Establece el color de fondo

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //Codigo que se ejecuta al harcer click sobre la foto
                datosPreferencias = new DatosPreferencias(activity);
                String idUsuarioInstagram = datosPreferencias.getIdUsuarioApi();
                String tokenInstagram = ConstantesRestApi.ACCESS_TOKEN;
                String idDispositivo = FirebaseInstanceId.getInstance().getToken(); //captura el token del dispositivo
                enviarLike(tokenInstagram, idFotoInstagram); //envia un like a la foto de instagram
                registrarDispositivoYUsuario(idDispositivo, idUsuarioInstagram);//guarda el dispositivo y el usuario de instagram en firebase
                registrarLike(idFotoInstagram, idUsuarioInstagram, idDispositivo);//guarda los datos del like en firebase
                notificarLike(idUsuarioInstagram); //envia las notificaciones
            }
        });
    }

    @Override
    public int getItemCount(){
        if(mascotas == null){
            return 0;
        } else {
            return mascotas.size();
        }
    }

    //**********  Clase interna MascotaViewHolder *****************
    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNumLikes;
        private LinearLayout llCardViewPerfil;

        // Constructor
        public PerfilViewHolder(View itemView){
            super(itemView);
            // Cargo todos las vistas del cardview
            this.imgFoto    = (ImageView) itemView.findViewById(R.id.imgFoto);;
            this.tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);
            // Agregado
            this.llCardViewPerfil = (LinearLayout) itemView.findViewById(R.id.llCardViewPerfil);
        }
    }

    // Metodo para guardar el token y el id de usuario instagram en la base de datos Firebase por intermedio de Heroku
    private void registrarDispositivoYUsuario(String idDispositivo, String idUsuarioInstagram){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionHeroku(); //Conecta con el servidor de Heroku
        //por último se utiliza el metodo que registra el token
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(idDispositivo, idUsuarioInstagram);
        // verificamos si salió bien
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                //Como la clase "UsuarioResponse" es identica a la respuesta no es necesario crear un deserializador
                UsuarioResponse usuarioResponse = response.body(); //obtiene la respuesta
                //aqui podemos guardar los datos localmente
                if (usuarioResponse!=null){
                    Log.d(TAG, "registrarDispositivoYUsuario: ID_FIREBASE ->"+ usuarioResponse.getId());
                    Log.d(TAG, "registrarDispositivoYUsuario: TOKEN FIREBASE ->"+ usuarioResponse.getToken());
                }else {
                    Log.d(TAG, "Error en el método \"registrarDispositivoYUsuario\" de la clase PerfilAdaptador " +
                            ", No hubo respuesta del servidor");
                }

            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d(TAG, "Huvo un error de conexión con FIREBASE!");
            }
        });
    }


    // Método POST, que al tocar sobre una foto envia un like a instagram
    private void enviarLike(String tokenInstagram, String idFotoInstagram){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiInstagram2(); //conecta con el servidor de Instagram2
        Call<LikeResponseInstagram>  likeResponseCall = endpoints.setLike(idFotoInstagram, tokenInstagram);//envía el like a la foto de instagram
        // verificamos si salió bien
        likeResponseCall.enqueue(new Callback<LikeResponseInstagram>() {
            @Override
            public void onResponse(Call<LikeResponseInstagram> call, Response<LikeResponseInstagram> response) {
                Toast.makeText(activity, "Has enviado un like a la foto", Toast.LENGTH_LONG).show();
               // String idDispositivo = FirebaseInstanceId.getInstance().getToken(); //captura el token del dispositivo
                Log.d(TAG, "LIKE OK. El like ha sido enviado a instagram");
            }
            @Override
            public void onFailure(Call<LikeResponseInstagram> call, Throwable t) {
                Log.d(TAG, "LIKE ERROR. Algo salió mal al dar like en la foto");
            }
        });
    }


    // Metodo POST, al tocar sobre una foto, esta petición guarda el like con los datos en la bd de Firebase
    private void registrarLike(String idFotoInstagram, String idUsuarioInstagram, String idDispositivo){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionHeroku(); //Conecta con el servidor de Heroku
        Call<LikeResponseHeroku> likeResponseHerokuCall = endpoints.registrarLike(idFotoInstagram, idUsuarioInstagram, idDispositivo);
        likeResponseHerokuCall.enqueue(new Callback<LikeResponseHeroku>() {
            @Override
            public void onResponse(Call<LikeResponseHeroku> call, Response<LikeResponseHeroku> response) {
                LikeResponseHeroku likeResponseHeroku = response.body();
                if (likeResponseHeroku!=null){
                    String registroLike = likeResponseHeroku.getId_dispositivo();
                }
                Log.d(TAG, "El like se ha guardado en Firebase utilizando HEROKU");
            }

            @Override
            public void onFailure(Call<LikeResponseHeroku> call, Throwable t) {
                Log.d(TAG, "Hubo un error al guardar el like en FIREBASE utilizando HEROKU");
            }
        });
    }

    // Metodo GET, para enviar notificaciones
    private void notificarLike(String idUsuarioInstagram){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionHeroku(); //Conecta con el servidor de Heroku
        final Call<NotificaLikeResponse> notificaLikeResponseCall = endpoints.notificaLike(idUsuarioInstagram);
        notificaLikeResponseCall.enqueue(new Callback<NotificaLikeResponse>() {
            @Override
            public void onResponse(Call<NotificaLikeResponse> call, Response<NotificaLikeResponse> response) {
                NotificaLikeResponse notificaLikeResponse = response.body();
                if (notificaLikeResponse!=null){
                    String id_dispositivo = notificaLikeResponse.getId_dispositivo();
                    Log.d(TAG, "NOTIFICACIÓN LIKE OK, Se han envíado las notificaciones a: " + id_dispositivo);
                }
            }

            @Override
            public void onFailure(Call<NotificaLikeResponse> call, Throwable t) {
                Log.d(TAG, "NOTIFICACIÓN ERROR, Hubo un error al intentar enviar las notificaciones");
            }
        });

    }


}
