package com.example.administrador.curso4_tarea4_1.restApi.adapter;

import com.example.administrador.curso4_tarea4_1.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.deserializador.MascotaDeserializador;
import com.example.administrador.curso4_tarea4_1.restApi.deserializador.PerfilDeserializador;
import com.example.administrador.curso4_tarea4_1.restApi.model.MascotaResponse;
import com.example.administrador.curso4_tarea4_1.restApi.model.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by administrador on 19/06/17.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.INSTAGRAM_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) // deserializacion automatica de los datos espesificos que le indica el Gson
                .build();
        return retrofit.create(EndpointsApi.class);
    }
    
    public EndpointsApi establecerConexionHeroku(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.HEROKU_ROOT_URL) //url base
                .addConverterFactory(GsonConverterFactory.create()) //Convierte la respuesta en gson
                .build();
        return retrofit.create(EndpointsApi.class);
    }

    // Aqui es donde se debe asociar cada Modelo de response con su correspondiente deserializador
    public Gson construyeGsonDeserializadorMediaRecent(){
        //objeto que sirve para que lo que se desearialize se lo asocie con el objeto Mascota
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador()); // define la asociación
        return  gsonBuilder.create();  // retorna el Gson con la asociacion creada
    }

    public Gson construyeGesonDeserializadorPerfil(){
        //objeto que sirve para que se desearializen los datos del perfil de usuario
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PerfilResponse.class, new PerfilDeserializador()); // define la asociación
        return  gsonBuilder.create();  // retorna el Gson con la asociacion creada
    }

    public EndpointsApi establecerConexionRestApiInstagram2(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.INSTAGRAM_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) // deserializacion automatica de los datos espesificos que le indica el Gson
                .build();
        return retrofit.create(EndpointsApi.class);
    }


}
