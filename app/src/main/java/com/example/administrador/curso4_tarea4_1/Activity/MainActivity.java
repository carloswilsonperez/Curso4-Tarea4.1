package com.example.administrador.curso4_tarea4_1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.example.administrador.curso4_tarea4_1.R;
import com.example.administrador.curso4_tarea4_1.adapter.PageAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.ConstantesRestApi;
import com.example.administrador.curso4_tarea4_1.restApi.DatosPreferencias;
import com.example.administrador.curso4_tarea4_1.restApi.EndpointsApi;
import com.example.administrador.curso4_tarea4_1.restApi.adapter.RestApiAdapter;
import com.example.administrador.curso4_tarea4_1.restApi.model.UsuarioResponse;
import com.example.administrador.curso4_tarea4_1.vista_fragment.HomeFragmentView;
import com.example.administrador.curso4_tarea4_1.vista_fragment.PerfilFragmentView;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DatosPreferencias datosPreferencias;
    String usuario;
    String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar     = (Toolbar)findViewById(R.id.toolbar);
        tabLayout   = (TabLayout)findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);

        //setUpViewPager(); //Activa los fragments

        if (toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el titulo del ToolBar
        }

        ImageView imgFavotitas = (ImageView)findViewById(R.id.imgFaboritas);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpViewPager(); //Activa los fragments
        // Verifica si hay un perfil guardado y si no hay le asigna el perfil por defecto
        datosPreferencias = new DatosPreferencias(this);
        usuario = datosPreferencias.getUsuarioApi();
        idUsuario = datosPreferencias.getIdUsuarioApi();
        if(usuario.isEmpty() || idUsuario.isEmpty()){
            datosPreferencias.setUsuarioApi(ConstantesRestApi.MI_USUARIO_SANDBOX);
            datosPreferencias.setIdUsuarioApi(ConstantesRestApi.MI_ID_USUARIO_SANDBOX);
        }
        Bundle bundle = getIntent().getExtras();//obtiene el codigo de llamada del PendigIntent de la notificación
        //si el intent no está vacío inicia en la pestaña 1
        if(bundle!=null){
            int numTab = bundle.getInt("numTab");
            if (numTab == 1) {
                // Abres la página que quieras
                viewPager.setCurrentItem(1); // 1 para ir a la segunda página ya que empiezan en 0
                Log.d("ABRIR-TAB", "estas dentro del if para abrir la pestaña 1");
            }else {
                Log.d("ABRIR-TAB", "estas FUERA del if ");
            }
        }
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.mContacto:
                Intent intent1 = new Intent(this, FormularioActivity.class);
                startActivity(intent1);
                break;

            case R.id.mAcercaDe:
                Intent intent2 = new Intent(this, AcercadeActivity.class);
                startActivity(intent2);
                break;
            case R.id.mConfigurarCuenta:
                Intent intent3 = new Intent(this, ConfiguraCuentaActivity.class);
                startActivity(intent3);
                break;
            case R.id.mRecibirNotificaciones:
                String token = FirebaseInstanceId.getInstance().getToken(); //captura el token
                Log.d("FIREBASE-TOKEN", token);
                enviarTokenRegistro(token); //lo envia para su registro
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    // Abre el activity2 con las 5 mascotas favoritas
    public void irFavoritas(View view){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    // Método para cargar el ArrayList con los fragments existentes
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        // Cargo los fragments en el órden que los quiero mostrar
        fragments.add(new HomeFragmentView());
        fragments.add(new PerfilFragmentView());
        return  fragments;
    }

    // Método para poner en orvita los fragments
    private void setUpViewPager(){
        // Se inicializa el viewPager con una instancia de la clase PageAdapter, se le pasa el manejador de fragments y
        // por último se llama a la funcion agregarFragments que devuelve el ArrayList con los fragments.
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perro);
    }

    // Metodo para enviar el tokens y el id de usuario instagram
    private void enviarTokenRegistro(String token){
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //instancio el adaptador
        EndpointsApi endpoints = restApiAdapter.establecerConexionHeroku(); //Conecta con el servidor de Heroku
        // Obtengo el id del usuario de instagram que está guardado en mi dispositivo
        datosPreferencias = new DatosPreferencias(this);
        idUsuario = datosPreferencias.getIdUsuarioApi();
        //por último se utiliza el metodo que registra el token
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(token, idUsuario);
        // verificamos si salió bien
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                //Como la clase "UsuarioResponse" es identica a la respuesta no es necesario crear un deserializador
                UsuarioResponse usuarioResponse = response.body(); //obtiene la respuesta
                //aqui podemos guardar los datos localmente
                Log.d("ID_FIREBASE", "Este es el ID ->"+ usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", "Este es el TOKEN ->"+ usuarioResponse.getToken());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d("ERROR_CONEXION_FIREBASE", "Huvo un error de conexión!");
            }
        });
    }


}
