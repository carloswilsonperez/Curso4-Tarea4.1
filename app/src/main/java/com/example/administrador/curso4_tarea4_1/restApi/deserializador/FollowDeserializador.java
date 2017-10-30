package com.example.administrador.curso4_tarea4_1.restApi.deserializador;

import com.example.administrador.curso4_tarea4_1.pojo.Follow;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by administrador on 29/10/17.
 */

//Clase para deserializar los datos y llevarlos a la forma de la clase del modelo Follow
public class FollowDeserializador implements JsonDeserializer<Follow> {
    @Override
    public Follow deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
