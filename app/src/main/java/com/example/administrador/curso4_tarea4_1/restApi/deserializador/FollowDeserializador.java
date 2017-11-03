package com.example.administrador.curso4_tarea4_1.restApi.deserializador;

import com.example.administrador.curso4_tarea4_1.restApi.model.FollowResponseInstagram;
import com.example.administrador.curso4_tarea4_1.restApi.model.JsonKeys;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by administrador on 29/10/17.
 */

//Clase para deserializar los datos y llevarlos a la forma de la clase del modelo Follow
public class FollowDeserializador implements JsonDeserializer<FollowResponseInstagram> {

    private static final String TAG = "FOLLOW_DESERIALIZADOR";

    @Override
    public FollowResponseInstagram deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        //Hola le indicamos que el json recibido queremos que se asemje a la clase response correspondiente
        FollowResponseInstagram followResponseInstagram = gson.fromJson(json, FollowResponseInstagram.class);
        //Obtengo el objeto data del json
        JsonObject followResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.DATA_RESPONSE_OBJECT);

        String estado_saliente = followResponseData.get(JsonKeys.FOLLOW_OUTGOING_STATUS).getAsString();
        String estado_entrada = followResponseData.get(JsonKeys.FOLLOW_INCOMING_STATUS).getAsString();
        String usuario_es_privado = followResponseData.get(JsonKeys.FOLLOW_TARGET_USER_IS_PRIVATE).getAsString();

        followResponseInstagram.setUsuarioObjetivoPrivado(usuario_es_privado);
        followResponseInstagram.setEstadoSaliente(estado_saliente);
        followResponseInstagram.setEstadoEntrante(estado_entrada);
        return followResponseInstagram;
    }


}
