package com.example.administrador.curso4_tarea4_1.restApi.deserializador;

import android.util.Log;

import com.example.administrador.curso4_tarea4_1.pojo.Follow;
import com.example.administrador.curso4_tarea4_1.restApi.model.FollowResponseInstagram;
import com.example.administrador.curso4_tarea4_1.restApi.model.JsonKeys;
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
 * Created by administrador on 29/10/17.
 */

//Clase para deserializar los datos y llevarlos a la forma de la clase del modelo Follow
public class FollowDeserializador implements JsonDeserializer<FollowResponseInstagram> {
    @Override
    public FollowResponseInstagram deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowResponseInstagram followResponseInstagram = gson.fromJson(json, FollowResponseInstagram.class);
        JsonArray followResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.DATA_RESPONCE_ARRAY);//Obtengo el array data del json
        followResponseInstagram.setFollows(deserializarFollowDeJson(followResponseData));
        return followResponseInstagram;
    }

    //
    public ArrayList<Follow> deserializarFollowDeJson(JsonArray followResponseData){

        ArrayList<Follow> follows = new ArrayList<>();

        for (int i = 0; i < followResponseData.size(); i++) { //Recorro el objeto followResponseData con la data para obtener los datos
            JsonObject followResponseDataObject = (JsonObject) followResponseData.get(i).getAsJsonObject(); //Obtiene un elemento objeto del array
            String outgoing_status = followResponseDataObject.get(JsonKeys.FOLLOW_OUTGOING_STATUS).getAsString();
   //         String incoming_status = followResponseDataObject.get(JsonKeys.FOLLOW_INCOMING_STATUS).getAsString();
   //         String target_user_is_private = followResponseDataObject.get(JsonKeys.FOLLOW_TARGET_USER_IS_PRIVATE).getAsString();
            Log.d("FOLLOW_DESERIALIZADOR", "el valor de outgoing_status es-> " + outgoing_status);
            Follow followActual = new Follow();
    //        followActual.setEstadoEntrada(incoming_status);
            followActual.setEstadoSalida(outgoing_status);
   //         followActual.setUsuarioPrivado(target_user_is_private);
            follows.add(followActual);
        }

        return follows;
    }
}
