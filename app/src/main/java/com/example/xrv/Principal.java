package com.example.xrv;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

//Clase en la que se establecen los componentes de la barra de navegación y la ventana en la que
// se mueven los fragment, junto con la funcionalidad de los botones
public class Principal extends AppCompatActivity {
    BottomNavigationView menu;
    KeyEvent event;
    int keyCode;
    String dato;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        menu = findViewById(R.id.menuInferior);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            dato=bundle.getString("email");
        }
        datoFragmentInicio(dato);

        //Metodo donde asociamos los botones de la barra de navegación a los fragmentos correspondientes.
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuInicio) {
                    datoFragmentInicio(dato);
                    return true;
                } else if (item.getItemId() == R.id.menuContacto) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new Contacto()).addToBackStack(null).commit();
                    return true;
                } else if (item.getItemId() == R.id.menuPerfil) {
                    //getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MiPerfil()).addToBackStack(null).commit();
                    datoFragmentPerfil(dato);
                    return true;
                }
                return false;
            }
        });
        salir();
        onKeyDown(keyCode,event);
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.i("Los fragmentos que hay son","Los fragmentos son" + count);
                }

    public boolean salir (){
        fragment = getSupportFragmentManager().findFragmentById(R.id.contenedor);
        if (fragment instanceof Inicio){
            return true;
        }
        return false;
        }
        //Método para preguntar al usuario si está seguro de querer abandonar la app al dar atrás en la pantalla principal
        public boolean onKeyDown (int keyCode, KeyEvent event){
        if(salir()==true){
            if(keyCode == event.KEYCODE_BACK){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea Salir de la aplicación?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        }
        return super.onKeyDown(keyCode,event);
    }
   //Métodos para dar funcionalidad a los botones del menu inferior de navegación
   private void datoFragmentInicio (String dato){
        Inicio inicio = new Inicio();
        Bundle mail = new Bundle();
        mail.putString("mail", dato);
        inicio.setArguments(mail);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contenedor,inicio).commit();
    }
    private void datoFragmentPerfil (String dato){
        MiPerfil miPerfil1 = new MiPerfil();
        Bundle mailPerfil = new Bundle();
        mailPerfil.putString("mailPerfil", dato);
        miPerfil1.setArguments(mailPerfil);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor,miPerfil1).addToBackStack(null).commit();

    }







}