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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class Principal extends AppCompatActivity {

    BottomNavigationView menu;
    KeyEvent event;
    int keyCode;

    String dato;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        menu = findViewById(R.id.menuInferior);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            dato=bundle.getString("email");
        }

        //Añadimos el fragmento Inicio al FrameLayout Contenedor.

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, new Inicio()).commit();

        //Metodo donde asociamos los botones de la barra de navegación a los fragmentos correspondientes.




        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuInicio) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new Inicio()).addToBackStack(null).commit();
                    return true;
                } else if (item.getItemId() == R.id.menuContacto) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new Contacto()).addToBackStack(null).commit();
                    return true;
                } else if (item.getItemId() == R.id.menuPerfil) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MiPerfil()).addToBackStack(null).commit();
                    return true;
                }
                return false;
            }


        });

    }


    /*public boolean onKeyDown(int keyCode, KeyEvent event, Context context) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            salir();
        }
        return super.onKeyDown(keyCode, event);

    }

    public void salir (){
        fragment = getSupportFragmentManager().findFragmentById(R.id.contenedor);
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
        }*/



}