package com.example.administrador.curso4_tarea4_1.bd;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.pojo.Mascota;
import java.util.ArrayList;

/**
 * Created by administrador on 27/05/17.
 */

public class ConstructorMascotas {

    private static final String TAG = "Depurador";
    Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    //Como standard siempre que se obtienen datos se deben devolver en un arraylist
    public ArrayList<Mascota> obtenerDatos(){

        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();  //instancio el ArrayList contactos
        BaseDatos db = new BaseDatos(context); // Intacio la clase BaseDatos
        mascotas = db.obtenerTodasLasMascotas(); // Primero obtengo todos los contactos, para verificar si la bd esta vacía
        if (mascotas.size() == 0) {
            insertarMascotas(db); // Si no hay contactos cargados los agrego
            return db.obtenerTodasLasMascotas();
        }else {
            return mascotas;
        }
    }

    // Datos inciales para cargar todas las mascotas
    public void insertarMascotas(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Pulgarcito");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro00);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro00);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 1

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Yaman");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro01);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro01);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 2

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Toby");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro02);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro02);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 3

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Peñarol");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro03);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro03);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 4

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Fausto");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro04);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro04);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 5

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Rafa");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro05);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro05);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 6

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Duke");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro06);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro06);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 7

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Spayck");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro07);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro07);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 8

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_NOMBRE, "Paco");
        contentValues.put(ConstantesDB.TABLE_MASCOTA_FOTO, R.drawable.perro08);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_COLOR_FONDO, R.color.fondo_perro08);
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, 0);
        db.insertarMascota(contentValues); // Mascota 9
    }

    // Primero obtine el numero de likes y luego le suma 1 y actualiza la base de datos
    public void darLikeMascota(Mascota mascota){

        Integer likes = obtenerLikesMascota(mascota); // Obtengo los likes que tiene la mascota
        likes=likes+1;
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTA_LIKES, likes);
        db.insertarLikeMascota(contentValues, mascota.getId());
        Log.d(TAG, "El valor de likes en 'darLikesMascota' es : "+ likes);
    }


    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context); // Instacio el objeto BaseDatos
        return db.obtenerLikesMascota(mascota); // Ejecuta el metodo para obtener el numero de likes y devuelve un integer
    }


    public ArrayList<Mascota> obtener5Datos(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();  //instancio el ArrayList contactos
        BaseDatos db = new BaseDatos(context); // Intacio la clase BaseDatos
        mascotas = db.obtener5Mascotas(); // trae las 5 mascotas
        return mascotas;
    }
}
