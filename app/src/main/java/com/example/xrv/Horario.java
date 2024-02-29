package com.example.xrv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Horario extends Fragment {


    View vista;
    RecyclerView recyclerView;
    ArrayList<RecyclerHorario> elementos;

    public Horario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        elementos = new ArrayList<RecyclerHorario>();
        elementos.add(new RecyclerHorario("ACCESO A DATOS", "9:00 - 10:00"));
        elementos.add(new RecyclerHorario("INGLÉS", "10:00 - 11:00"));
        elementos.add(new RecyclerHorario("DESARROLLO DE INTERFACES", "11:00 - 12:00"));
        elementos.add(new RecyclerHorario("EMPRESA E INICIATIVA EMPRENDEDORA", "12:00 - 13:00"));
        elementos.add(new RecyclerHorario("PROGRAMACIÓN DE SERVICIOS Y PROCESOS", "13:00 - 14:00"));
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_horario, container, false);

        init();
                return vista;
    }

    public void init(){
        AdaptadorHorario adaptadorHorario = new AdaptadorHorario(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerHorario);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorHorario);
    }
}