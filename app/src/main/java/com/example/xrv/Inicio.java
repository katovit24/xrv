package com.example.xrv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.internal.CollapsingTextHelper;

import java.util.Objects;


public class Inicio extends Fragment {


    Button horario, aulas, notas, asignaturas;
    View vista;
    Inicio inicio;
    Principal principal = new Principal();
    Context context = getContext();
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        identifica(vista);
        
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contenedor,new Horario(),null);
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });
        aulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contenedor,new Aulas(),null);
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();


            }
        });
        notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contenedor,new Notas(),null);
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });
        asignaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contenedor,new Asignaturas(),null);
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });


        

        return vista;
    }



    
    private void identifica (View vista){
        horario = vista.findViewById(R.id.horario);
        aulas = vista.findViewById(R.id.aulas);
        notas = vista.findViewById(R.id.notas);
        asignaturas = vista.findViewById(R.id.asignaturas);
    }

    public void onBackPressed(){

            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setMessage("¿Desea Salir de la aplicación?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    inicio.onDestroy();
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
