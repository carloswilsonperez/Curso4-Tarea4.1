package com.example.administrador.curso4_tarea4_1.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.pojo.Perfil;
import com.example.administrador.curso4_tarea4_1.presentador.PerfilFragmentPresenter;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private static final String TAG = "ConfiguraCuentaActivity";

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
                                datosPerfil.setIdUsuarioApi(idPerfil);// Guarda el id ingresado en preferencias
                                Toast.makeText(ConfiguraCuentaActivity.this, "El usuario \'"+ nombreSandbox + "\' con el id \'" + idPerfil +"\' ha sido guardado.", Toast.LENGTH_LONG).show();
                                etNombreSandbox.setText(""); //borra el contenido del EditText
                                String idDispositivo = FirebaseInstanceId.getInstance().getToken(); //captura el token del dispositivo
                                registrarDispositivoYUsuario(idDispositivo, idPerfil);
                            }else{
                                Toast.makeText(ConfiguraCuentaActivity.this, "No se puedo obtener el id del usuario \'"+ nombreSandbox + "\' ", Toast.LENGTH_LONG).show();
                            }
                        }
                    },3000);

                }else {
                    Toast.makeText(ConfiguraCuentaActivity.this, "No hay ningún nombre para guardar", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public static void recibeIdPerfil(String idPerfilInstagram){
        idPerfil = idPerfilInstagram;
    }

    // Metodo para guardar el token y el id de usuario de instagram en la base de datos Firebase por intermedio de Heroku
    private void registrarDispositivoYUsuario(String idDispositivo, String idUsuarioInstagram){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionHeroku(); //Conecta con el servidor de Heroku
        //por último se utiliza el metodo que registra el token
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(idDispositivo, idUsuarioInstagram);
        // verificamos si salió bien
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                //Como la clase "UsuarioResponse" es identica a la respuesta no es necesario crear un deserializador
                UsuarioResponse usuarioResponse = response.body(); //obtiene la respuesta
                //aqui podemos guardar los datos localmente
                if (usuarioResponse!=null){
                    Log.d(TAG, "registrarDispositivoYUsuario: ID_FIREBASE ->"+ usuarioResponse.getId());
                    Log.d(TAG, "registrarDispositivoYUsuario: TOKEN FIREBASE ->"+ usuarioResponse.getToken());
                }else {
                    Log.d(TAG, "Error en el método \"registrarDispositivoYUsuario\" de la clase PerfilAdapter " +
                            ", No hubo respuesta del servidor");
                }
            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d(TAG, "Huvo un error de conexión con FIREBASE!");
            }
        });
    }
}
