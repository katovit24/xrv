package com.example.xrv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Principal extends AppCompatActivity {

BottomNavigationView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        menu = findViewById(R.id.menuInferior);

        //Añadimos el fragmento Inicio al FrameLayout Contenedor.

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, new Inicio()).commit();

        //Metodo donde asociamos los botones de la barra de navegación a los fragmentos correspondientes.

        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menuInicio){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new Inicio()).addToBackStack("").commit();
                    return true;
                }else if(item.getItemId()==R.id.menuContacto){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new Contacto()).addToBackStack("").commit();
                    return true;
                }else if(item.getItemId()==R.id.menuPerfil){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new MiPerfil()).addToBackStack("").commit();
                    return true;
                }
                return false;
            }

        });

        //getOnBackPressedDispatcher();

    }







}