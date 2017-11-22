package com.example.administrador.curso4_tarea4_1.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by administrador on 19/11/17.
 */

public class MediaUsuarioAdapter extends RecyclerView.Adapter<MediaUsuarioAdapter.ViewHolder> {

    private static final String TAG = "MediaUsuarioAdapter";
    ArrayList<Mascota> mascotas;
    Activity activity;

    // Constructor
    public MediaUsuarioAdapter(ArrayList<Mascota> mascotas, Activity activity){
        this.activity = activity;
        this.mascotas = mascotas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_media_usuario, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Mascota mascota = mascotas.get(position); //Obtiene todos los datos de la mascota en la posición position
        String ruta = mascota.getUrlFoto();
        final String idFotoInstagram = mascota.getIdFoto();
        ruta = ruta.replaceAll("\"", ""); //Quito las comillas dobles que vienen con la url desde el json
        Picasso.with(activity).
                load(ruta).
                into(viewHolder.imgFotoMedia);
        Log.d(TAG, "La ruta la url es:" + ruta);
        viewHolder.textoMedia.setText("Foto");
    }

    @Override
    public int getItemCount() {
        if(mascotas == null){
            return 0;
        } else {
            return mascotas.size();
        }
    }

    //**********  Clase interna ViewHolder *****************
    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvMediaUsuario;
        private ImageView imgFotoMedia;
        private TextView textoMedia;
        public ViewHolder(View itemView) {
            super(itemView);
            // Cargo todas las vistas del cardview
            cvMediaUsuario = (CardView) itemView.findViewById(R.id.cvMediaUsuario);
            imgFotoMedia = (ImageView)itemView.findViewById(R.id.imgFotoMedia);
            textoMedia = (TextView) itemView.findViewById(R.id.textoMedia);
        }
    }
}
