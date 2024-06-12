package com.example.xrv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Horario extends Fragment {


    View vista;
    RecyclerView recyclerView;
    ArrayList<RecyclerHorario> elementos;
    RequestQueue requestQueue;
    AdaptadorHorario adaptadorHorario;
    String email;

    public Horario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_horario, container, false);

        elementos = new ArrayList<>();
        String x=null;
        requestQueue = Volley.newRequestQueue(getContext());
        email = getArguments().getString("mailHorario");
        Log.i("El horario es", "El mail del horario es" + email);
        dameHorario(email, elementos);

        return vista;
    }

    public void init(){
        AdaptadorHorario adaptadorHorario = new AdaptadorHorario(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerHorario);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorHorario);
    }

    private void dameHorario(String email, ArrayList<RecyclerHorario> elementos) {

    /*
    Método privado que recibe un String y un Arraylist de la clase RecyclerHorario
    Se crea un objeto JsonArrayRequest que recibe una petición GET, una URL que lleva el mail que se
    proporciona por parametro, un listener de respuesta a través del cual se rellena el arraylist
    que se da por parámetro, se ordena con el método sort
     */
        //String URL ="http://192.168.1.143/xrv/horario.php?email=" + email;
        String URL ="http://192.168.0.133/xrv/horario.php?email=" + email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i <response.length(); i++){
                        JSONObject jsonObject = new JSONObject(response.get(i).toString());
                        String hrAsignatura = jsonObject.getString("asignatura").toUpperCase();
                        String hrHorario = jsonObject.getString("horario").toUpperCase();
                        elementos.add(new RecyclerHorario(hrAsignatura,hrHorario));
                        Log.i("valorList", "El valor es" + elementos);
                    }
                    Collections.sort(elementos, new Comparator<RecyclerHorario>() {
                        @Override
                        public int compare(RecyclerHorario recyclerHorario, RecyclerHorario t1) {

                            return recyclerHorario.hrHorario.compareToIgnoreCase(t1.hrHorario);
                        }
                    });
                    adaptadorHorario = new AdaptadorHorario(elementos,getContext());
                    init();
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Conexión erronea", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}