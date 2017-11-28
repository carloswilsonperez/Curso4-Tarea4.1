package com.example.administrador.curso4_tarea4_1.restApi.deserializador;

import com.example.administrador.curso4_tarea4_1.pojo.Perfil;
import com.example.administrador.curso4_tarea4_1.restApi.model.JsonKeys;
import com.example.administrador.curso4_tarea4_1.restApi.model.PerfilResponse;
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
 * Created by administrador on 02/07/17.
 */

public class PerfilDeserializador implements JsonDeserializer<PerfilResponse>{

    private static final String TAG = "PerfilDeserializador";

    @Override
    public PerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PerfilResponse perfilResponse = gson.fromJson(json, PerfilResponse.class);//
        JsonArray perfilResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.DATA_RESPONCE_ARRAY);//Obtiene el array data del json
        perfilResponse.setPerfil(deserealizarPerfilDeJson(perfilResponseData));//Llama al metodo contiguo para deserializar
        return perfilResponse; // Devuelve un objeteto de tipo PerfilResponse con la respuesta
    }

    public ArrayList<Perfil> deserealizarPerfilDeJson(JsonArray perfilResponseData){
        ArrayList<Perfil> perfiles = new ArrayList<>();
        for (int i = 0; i < perfilResponseData.size(); i++) {
            JsonObject perfilResponseDataObject = (JsonObject) perfilResponseData.get(i).getAsJsonObject();//Obtiene un elemento objeto del array
            String idUsuario = perfilResponseDataObject.get(JsonKeys.USER_ID).getAsString(); //obtiene el id del perfil de usuario
            String nombreUsuario = perfilResponseDataObject.get(JsonKeys.USER_NAME).getAsString(); //obtiene el nombre de usuario
            String nombreCompleto = perfilResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString(); //obtiene el nombre
            String urlFotoPerfil = perfilResponseDataObject.get(JsonKeys.PROFILE_PICTURE).toString(); //Obtiene la url de la foto de perfil

            // Lleno los datos del usuarioApi actual
            Perfil perfilActual = new Perfil();
            perfilActual.setIdUsuario(idUsuario);
            perfilActual.setNombreUsuario(nombreUsuario);
            perfilActual.setNombreCompleto(nombreCompleto);
            perfilActual.setUrlFotoPerfil(urlFotoPerfil);
            perfiles.add(perfilActual); //Guardo al perfil actual en el array perfiles
        }

        return perfiles;
    }

}
