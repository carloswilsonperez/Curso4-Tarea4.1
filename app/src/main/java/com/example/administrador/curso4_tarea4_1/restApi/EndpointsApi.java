package com.example.administrador.curso4_tarea4_1.restApi;

import com.example.administrador.curso4_tarea4_1.restApi.model.FollowResponseInstagram;
import com.example.administrador.curso4_tarea4_1.restApi.model.LikeResponseHeroku;
import com.example.administrador.curso4_tarea4_1.restApi.model.LikeResponseInstagram;
import com.example.administrador.curso4_tarea4_1.restApi.model.MascotaResponse;
import com.example.administrador.curso4_tarea4_1.restApi.model.NotificaLikeResponse;
import com.example.administrador.curso4_tarea4_1.restApi.model.PerfilResponse;
import com.example.administrador.curso4_tarea4_1.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by administrador on 19/06/17.
 */

// Interface que genera las peticiones a la api
public interface EndpointsApi {
    //Obtiene los medios más recientes publicados por un usuario: https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/5623708812/media/recent/?access_token=5557323253.5477f1a.a6c8d1cf0f9747fe91b9c884bc63fcc4
    @GET("users/{id}/media/recent/")//Peticion GET a la api de instagram que va a ser usada por el metodo seguido
    Call<MascotaResponse> getRecentMedia(@Path("id") String idUsuarioInstagram,
                                         @Query("access_token") String tokenInstagram); //Retrofit necesita usar la clase Call

    /* GET PARA BUSCAR LOS USUARIOS POR NOMBRE, a partir de ahi podemos obtener el id y luego hacer mas consultas
    https://api.instagram.com/v1/users/search?q=supermascota5&access_token=5557323253.5477f1a.a6c8d1cf0f9747fe91b9c884bc63fcc4   */
    @GET("users/search") //
    Call<PerfilResponse> getPerfil(@Query("q") String usuarioInstagram,
                                   @Query("access_token") String tokenInstagram);

    // Registra el id del dispositivo y el id de usuario de instagram que está utilizando
    @FormUrlEncoded
    @PUT(ConstantesRestApi.PUT_DISPOSITIVO_USUARIO)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String idDispositivo,
                                           @Field("id_usuario_instagram") String idUsuarioInstagram);


    /*  Petición POST para darle like a una foto de instagram
        https://api.instagram.com/v1/media/{media-id}/likes   */
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_SET_LIKE_MEDIA)
    Call<LikeResponseInstagram> setLike(@Path("media-id") String idFotoInstagram,
                                        @Field("access_token") String tokenInstagram);


    /*  Petición POST al Servidor web Heroku para guardar el like junto con sus datos en la bd de Firebase
       https://https://desolate-stream-12439.herokuapp.com/likes   */
    @FormUrlEncoded
    @POST(ConstantesRestApi.POST_LIKE)
    Call<LikeResponseHeroku> registrarLike(@Field("id_foto_instagram") String idFotoInstagram,
                                           @Field("id_usuario_instagram") String idUsuarioInstagram,
                                           @Field("id_dispositivo") String idDispositivo);

    /* GET PARA enviar notificaciones a los susuarios que tengan este id_usuarios propietarios de la foto
    https://desolate-stream-12439.herokuapp.com/notifica-like/:id_usuario_instagram  */
    @GET("notifica-like/{id_usuario_instagram}/")
    Call<NotificaLikeResponse> notificaLike(@Path("id_usuario_instagram") String idUsuarioInstagram);


    /*  Petición POST para seguir o dejar de seguir a un usuario de instagram
        https://api.instagram.com/v1/users/{user-id}/relationship   */
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_FOLLOW_UNFOLLOW)
    Call<FollowResponseInstagram> seguirUsuarioInstagram(@Path("users-id") String idUsuarioInstagram,
                                                         @Field("access_token") String tokenInstagram,
                                                         @Field("action") String accion );
}
