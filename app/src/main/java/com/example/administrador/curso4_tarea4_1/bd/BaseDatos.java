package com.example.administrador.curso4_tarea4_1.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrador.curso4_tarea4_1.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by administrador on 27/05/17.
 */

public class BaseDatos extends SQLiteOpenHelper {


    Context context;
    private static final String TAG = "BaseDatos";

    // Constructor
    public BaseDatos(Context context) {
        super(context, ConstantesDB.DATABASE_NAME, null, ConstantesDB.DATABASE_VERSION);
        this.context = context;
    }



    @Override // Crea la tabla mascota si no existe
    public void onCreate(SQLiteDatabase db) {

        // query para crear la tabla mascota
        String queryCrearTablaMascota =
                "CREATE TABLE "+ConstantesDB.TABLE_MASCOTA+"("+
                        ConstantesDB.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        ConstantesDB.TABLE_MASCOTA_NOMBRE + " TEXT, "+
                        ConstantesDB.TABLE_MASCOTA_FOTO + " TEXT, "+
                        ConstantesDB.TABLE_MASCOTA_COLOR_FONDO + " TEXT, "+
                        ConstantesDB.TABLE_MASCOTA_LIKES + " INTEGER"+
                        ")";
        db.execSQL(queryCrearTablaMascota);
    }

    @Override // Si la tabla mascota cambia de versión la borra y crea la nueva
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesDB.TABLE_MASCOTA);
        onCreate(db);
    }
    /*
        ************** Metodos propios *************************************************************
     */


    // método para obtener todos las mascotas, que devuelve un ArrayList de tipo Mascota
    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>(); // Instacia el ArrayList para devolver
        String query = "SELECT * FROM " + ConstantesDB.TABLE_MASCOTA; // Consulta sql
        SQLiteDatabase db = this.getWritableDatabase(); // Abre la base de datos en modo escritura
        Cursor registros = db.rawQuery(query, null); //Obtiene todas los filas y las guarda en objeto de tipo cursor registros
        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getString(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setUrlFoto(registros.getString(2));
            mascotaActual.setColorFondo(registros.getInt(3));
            mascotaActual.setLikes(registros.getInt(4));
            mascotas.add(mascotaActual);
        }
        db.close(); // Cierra la conexión a la BD
        return mascotas;
    }

    //  Método para ingresar una nueva mascota. Este recibe como argumento un contenedor de valores
    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase(); // Abre la base de datos en modo escritura
        db.insert(ConstantesDB.TABLE_MASCOTA, null, contentValues);
        db.close();
    }


    // Método para insertar 1 like a una mascota
    public void insertarLikeMascota(ContentValues contentValues, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(ConstantesDB.TABLE_MASCOTA, contentValues, "id="+id, null);
        db.close();
    }



    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query = "SELECT "+ ConstantesDB.TABLE_MASCOTA_LIKES +
                " FROM " + ConstantesDB.TABLE_MASCOTA +
                " WHERE " + ConstantesDB.TABLE_MASCOTA_ID +"="+mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase(); //abro la conexión
        Cursor registros = db.rawQuery(query, null); // ejecuta la consulta y obtengo los regitros
        if (registros.moveToNext()){
            likes = registros.getInt(0); //Como tengo un valor solo obtengo la columna con index 0
            Log.d(TAG, "El valor de likes en 'ObtenerLikesMascota' es : "+ likes);        }

        db.close(); // Cierra la conexión
        return likes;
    }

    // método para obtener las 5 mascotas con mas likes, que devuelve un ArrayList con las 5 mascotas
    public ArrayList<Mascota> obtener5Mascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>(); // Instacia el ArrayList para devolver
        String query = "SELECT * FROM "+ConstantesDB.TABLE_MASCOTA+
                        " ORDER BY "+ConstantesDB.TABLE_MASCOTA_LIKES+
                        " DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase(); // Abre la base de datos en modo escritura
        Cursor registros = db.rawQuery(query, null); //Obtiene todas los filas y las guarda en objeto de tipo cursor registros
//        String foto= registros.getString(2);
        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getString(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setUrlFoto(registros.getString(2));
            mascotaActual.setColorFondo(registros.getInt(3));
            mascotaActual.setLikes(registros.getInt(4));
            mascotas.add(mascotaActual);
            Log.d("NOMBRE-> "+registros.getString(1), "REGISTROS_BD");
            Log.d("FOTO-> "+registros.getString(2), "REGISTROS_BD");
            Log.d("COLOR_FONDO-> "+registros.getString(3), "REGISTROS_BD");
        }
        db.close(); // Cierra la conexión a la BD
        return mascotas;
    }

}
