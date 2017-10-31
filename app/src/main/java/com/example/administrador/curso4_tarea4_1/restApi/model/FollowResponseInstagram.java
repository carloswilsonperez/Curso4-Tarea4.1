package com.example.administrador.curso4_tarea4_1.restApi.model;

import com.example.administrador.curso4_tarea4_1.pojo.Follow;

import java.util.ArrayList;

/**
 * Created by administrador on 28/10/17.
 */

// Clase para manejar la respuesta de instagram al seguir un usuuario, dado que devuelve un array
public class FollowResponseInstagram {

    ArrayList<Follow> follows;

    public FollowResponseInstagram() {

    }

    public FollowResponseInstagram(ArrayList<Follow> follows) {
        this.follows = follows;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }

    public void setFollows(ArrayList<Follow> follows) {
        this.follows = follows;
    }
}
