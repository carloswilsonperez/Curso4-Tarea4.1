package com.example.administrador.curso4_tarea4_1.restApi;

/**
 * Created by administrador on 19/06/17.
 */

public final class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String INSTAGRAM_ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5557323253.5477f1a.a6c8d1cf0f9747fe91b9c884bc63fcc4";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/"; // Obtener los medios recientes
    public static final String KEY_SET_LIKE_MEDIA = "media/{media-id}/likes";          // Dar me gusta a una foto
    public static final String KEY_FOLLOW_UNFOLLOW = "users/{user-id}/relationship"; //Seguir usuario de instagram
    public static final String KEY_QUERY_FOLLOW_ = "users/{user-id}/relationship"; //Consulta si se está siguiendo al usuario de instagram


    // ************  SharedPrecerences  **************
    public static final String MI_SHARED_PREFERENCES = "MisDatos"; // nombre del SharedPreferences
    public static final String MI_USUARIO_SANDBOX = "supermascota5"; // nombe del usuario por default
    public static final String MI_ID_USUARIO_SANDBOX = "5557323253"; // id del usuario por default
    public static final String key_USUARIO_API = "usuario_api"; // llave para el nombre del xml
    public static final String key_ID_USUARIO_API = "id_usuario_api"; // llave para el nombre del xml

    // ************** Rutas servidor Rest Heroku ***************************************
    public static final String HEROKU_ROOT_URL = "https://young-fjord-96317.herokuapp.com/";
    public static final String PUT_DISPOSITIVO_USUARIO = "dispositivo-usuario/"; //guarda el dispositivo y el usuario en la bd
    public static final String POST_LIKE = "likes/"; //guarda el id_foto_instagram el id_usuario_instagram y el id_dispositivo en la bd
    public static final String GET_NOTIFICA_LIKE = "notifica-like/"; //envia las notificaciones a los que esten usando ese id_usuario_instagram

}
