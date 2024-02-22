package com.example.xrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView registro;
    EditText email;
    EditText password;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifica();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                acceso();
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });

    }



    private void identifica (){
        registro = findViewById(R.id.registrate);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        boton = findViewById(R.id.botonEntrar);
    }
    private void registrar (){
        Intent registrar = new Intent(this,Registro.class);
        startActivity(registrar);
    }
    private void acceso (){
        Intent acceder = new Intent(this, Principal.class);
        startActivity(acceder);
        finish();
    }


}