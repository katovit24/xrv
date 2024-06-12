package com.example.xrv;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notas extends Fragment  {

    public Notas() {

    }
    View vista;
    RecyclerView recyclerView;
    ArrayList<CardViewNotas> elementos;
    RequestQueue requestQueue;
    AdaptadorNotas adaptadorNotas;
    String email;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_notas, container, false);
        elementos = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getContext());

        email = getArguments().getString("mailNotas");


        dameNotas(email,elementos);

        Log.i("valorList", "El valor es" + elementos);

        return vista;
    }

    public void init(){


        AdaptadorNotas adaptadorNotas = new AdaptadorNotas(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerNotas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorNotas);

    }

    private void dameNotas (String email,  ArrayList<CardViewNotas> elementos){

        //String URL ="http://192.168.1.143/xrv/notas.php?email=" + email;
        String URL ="http://192.168.0.133/xrv/notas.php?email=" + email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    try {
                        for (int i=0; i <response.length(); i++){
                            JSONObject jsonObject = new JSONObject(response.get(i).toString());
                            String ntAsignatura = jsonObject.getString("asignatura").toUpperCase();
                            String ntNota = jsonObject.getString("nota").toUpperCase();
                            elementos.add(new CardViewNotas(ntAsignatura,ntNota));

                            Log.i("valorList", "El valor es" + elementos);
                        }
                        adaptadorNotas = new AdaptadorNotas(elementos,getContext());
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