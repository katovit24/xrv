package com.example.xrv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Asignaturas extends Fragment {

    View vista;
    RecyclerView recyclerView;
    ArrayList<CardViewAsignaturas> elementos;

    public Asignaturas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elementos = new ArrayList<CardViewAsignaturas>();
        elementos.add(new CardViewAsignaturas("ACCESO A DATOS", "NEREIDA HERNANDEZ HERNANDEZ"));
        elementos.add(new CardViewAsignaturas("INGLÉS", "FRANCISCO MARTINEZ GALVEZ"));
        elementos.add(new CardViewAsignaturas("DESARROLLO DE INTERFACES", "NEREIDA HERNANDEZ HERNANDEZ"));
        elementos.add(new CardViewAsignaturas("EMPRESA E INICIATIVA EMPRENDEDORA", "ISMAEL GOMEZ ACEVEDO"));
        elementos.add(new CardViewAsignaturas("PROGRAMACIÓN DE SERVICIOS Y PROCESOS", "NEREIDA HERNANDEZ HERNANDEZ"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_asignaturas, container, false);

        init();
        return vista;
    }

    public void init(){
        AdaptadorAsignaturas adaptadorAsignaturas = new AdaptadorAsignaturas(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerAsignaturas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorAsignaturas);
    }
}