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

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.internal.CollapsingTextHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
//Clase para la pantalla inicial de la aplicación, en la que se cargan vistas y se ejecutan eventos por parte
// de las acciones del usuario
public class Inicio extends Fragment {
    Button horario, aulas, notas, asignaturas;
    View vista;
    TextView bienvenida;
    Inicio inicio;
    RequestQueue requestQueue;
    String mailLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }
    //Método en que se infla la vista de la pantalla principal y se ejecutan los eventos correspondientes
    // a la interacción del usuario
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        requestQueue = Volley.newRequestQueue(getContext());
        identifica(vista);
        Bundle bundle = getArguments();
        if (bundle!=null){
            mailLogin = getArguments().getString("mail");
        }
        //Log.i("Mi dato de mail", "El email es: " + mailLogin);
        dameSaludo();

        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datoFragmentHorario(mailLogin);
            }
        });
        aulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datoFragmentAulas(mailLogin);
            }
        });
        notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datoFragmentNotas(mailLogin);
            }
        });
        asignaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datoFragmentAsignaturas(mailLogin);
            }
        });
        return vista;
    }
    //Método que asigna a los objetos la vista correspondiente
    private void identifica (View vista){
        bienvenida = vista.findViewById(R.id.bienvenida);
        horario = vista.findViewById(R.id.horario);
        aulas = vista.findViewById(R.id.aulas);
        notas = vista.findViewById(R.id.notas);
        asignaturas = vista.findViewById(R.id.asignaturas);
    }
    //Método para preguntar al usuario si está seguro de querer abandonar la app al dar atrás en la pantalla principal
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
    //Método en el que se hace la petición para saludar por su nombre al usuario que acceda en la pantalla principal
    private void dameSaludo(){
        //String URL ="http://192.168.1.143/xrv/datosPersonales.php?email=" + mailLogin;
        String URL ="http://192.168.0.133/xrv/datosPersonales.php?email=" + mailLogin;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.get(0).toString());
                            String perNombre = jsonObject.getString("nombre");
                            bienvenida.setText("BIENVENID@ A XRV" + "\n" + perNombre);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    //Métodos que envían el mail como parámetro al pulsar el botón para obtener la información en la vista correspondiente
    private void datoFragmentHorario (String dato){
        Horario horario1 = new Horario();
        Bundle mailHorario = new Bundle();
        mailHorario.putString("mailHorario", dato);
        horario1.setArguments(mailHorario);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contenedor,horario1).addToBackStack(null).commit();
    }
    private void datoFragmentNotas (String dato){

        Notas notas1 = new Notas();
        Bundle mailNotas = new Bundle();
        mailNotas.putString("mailNotas", dato);
        notas1.setArguments(mailNotas);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contenedor,notas1).addToBackStack(null).commit();
    }
    private void datoFragmentAulas (String dato){

        Aulas aulas1 = new Aulas();
        Bundle mailAulas = new Bundle();
        mailAulas.putString("mailAulas", dato);
        aulas1.setArguments(mailAulas);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contenedor,aulas1).addToBackStack(null).commit();
    }
    private void datoFragmentAsignaturas (String dato){
        Asignaturas asignaturas1 = new Asignaturas();
        Bundle mailAsignaturas = new Bundle();
        mailAsignaturas.putString("mailAsignaturas", dato);
        asignaturas1.setArguments(mailAsignaturas);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contenedor,asignaturas1).addToBackStack(null).commit();

    }



}
