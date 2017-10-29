package com.example.administrador.curso4_tarea4_1.restApi.deserializador;

import android.util.Log;

import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import com.example.administrador.curso4_tarea4_1.restApi.model.JsonKeys;
import com.example.administrador.curso4_tarea4_1.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by administrador on 19/06/17.
 */

//Clase para deserializar los datos y llevarlos a la forma de la clase del modelo MascotaResponse
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse>{

    private static final String TAG = "MascotaDeserializador";

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);//
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.DATA_RESPONCE_ARRAY);//Obtengo el array data del json
        mascotaResponse.setMascotas(deserealizarMascotaDeJson(mascotaResponseData));//llama al metodo contiguo para deserializar
        return mascotaResponse; // Devuelve un objteto con de tipo response con la respuesta
    }



    public ArrayList<Mascota> deserealizarMascotaDeJson(JsonArray mascotaResponseData){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = (JsonObject) mascotaResponseData.get(i).getAsJsonObject();//Obtiene un elemento objeto del array
            String idFoto = mascotaResponseDataObject.get(JsonKeys.idFoto).getAsString();
            JsonObject usuarioJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER); //obtiene el objeto usuarioApi
            String id = usuarioJson.get(JsonKeys.USER_ID).getAsString(); //obtiene el id del usuarioApi
            Log.d(TAG, "el valor del id es-> " + id);
            String nombreCompleto = usuarioJson.get(JsonKeys.USER_FULLNAME).getAsString(); //obtiene el nombre

            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);// otiene el objeto imagen del json
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION); // obtiene el objeto resolucion que dentro de imgen
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).toString(); //Obtiene la url de la foto

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES); //Obtiene el objeto likes
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt(); //Obtiene el numero de likes
            // Lleno los datos del usuarioApi actual
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombre(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);
            mascotaActual.setIdFoto(idFoto);

            mascotas.add(mascotaActual); //Guardo al usuarioApi actual en el array mascotas
        }

        return mascotas;
    }


}
