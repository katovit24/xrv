package com.example.xrv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Aulas extends Fragment {


    View vista;
    RecyclerView recyclerView;
    ArrayList <CardViewAulas> elementos;
    public Aulas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        elementos = new ArrayList<CardViewAulas>();
        elementos.add(new CardViewAulas("ACCESO A DATOS", "L16"));
        elementos.add(new CardViewAulas("INGLÉS", "L14"));
        elementos.add(new CardViewAulas("DESARROLLO DE INTERFACES", "L17"));
        elementos.add(new CardViewAulas("EIE", "L15"));
        elementos.add(new CardViewAulas("PROGRAMACIÓN DE SERVICIOS", "L15"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_aulas, container, false);

        init();

        return vista;
    }


    public void init(){

        AdaptadorAulas adaptadorAulas = new AdaptadorAulas(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerAulas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorAulas);

    }
}