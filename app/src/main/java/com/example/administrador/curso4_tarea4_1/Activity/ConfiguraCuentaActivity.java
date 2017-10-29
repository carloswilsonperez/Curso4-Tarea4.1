package com.example.administrador.curso4_tarea4_1.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.pojo.Perfil;
import com.example.administrador.curso4_tarea4_1.presentador.PerfilFragmentPresenter;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;

import java.util.ArrayList;

/**
 * Created by administrador on 18/06/17.
 */

public class ConfiguraCuentaActivity extends AppCompatActivity {

    EditText etNombreSandbox;
    Button btnGuardarCuenta;
    Toolbar toolbar;
    PerfilFragmentPresenter perfilFragmentPresenter;
    static String idPerfil;
    Handler mhandler=new Handler();
    DatosPreferencias datosPerfil;
    String nombreSandbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracuenta);

        etNombreSandbox     = (EditText) findViewById(R.id.etNombreSandbox);
        btnGuardarCuenta    = (Button) findViewById(R.id.btnGuardarCuenta);
        toolbar             = (Toolbar)findViewById(R.id.toolbar);

        if (toolbar!=null){
            setSupportActionBar(toolbar);
        //    getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el titulo del ToolBar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // boton para atras
        }

        //etNombreSandbox.setInputType(InputType.TYPE_NULL);

        //********** Al presionar el botón "Guardar Cuenta" **********
        btnGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilFragmentPresenter = new PerfilFragmentPresenter();
                datosPerfil = new DatosPreferencias(getApplicationContext());
                nombreSandbox = etNombreSandbox.getText().toString(); //obtengo el contenido del EditText

                if(nombreSandbox != null && !nombreSandbox.isEmpty()){
                    perfilFragmentPresenter.obtenerIdPerfil(nombreSandbox);
                    mhandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (idPerfil!=null && !idPerfil.isEmpty()){
                                datosPerfil.setUsuarioApi(nombreSandbox); // Guarda el usuario ingresado en preferencias
                                datosPerfil.setIdUsuarioApi(idPerfil);// Guarda el id obtenido en preferencias
                                Toast.makeText(ConfiguraCuentaActivity.this, "El usuario \'"+ nombreSandbox + "\' con el id \'" + idPerfil +"\' ha sido guardado.", Toast.LENGTH_LONG).show();
                                etNombreSandbox.setText(""); //borra el contenido del EditText
                            }else{
                                Toast.makeText(ConfiguraCuentaActivity.this, "No se puedo obtener el id del usuario \'"+ nombreSandbox + "\' ", Toast.LENGTH_LONG).show();
                            }
                        }
                    },1500);

                }else {
                    Toast.makeText(ConfiguraCuentaActivity.this, "No hay ningún nombre para guardar", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    public static void recibeIdPerfil(ArrayList<Perfil> perfiles){
        idPerfil = perfiles.get(0).getIdUsuario();
    }
}
