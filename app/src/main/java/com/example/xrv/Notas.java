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

public class Notas extends Fragment {

    public Notas() {

    }
    View vista;
    RecyclerView recyclerView;
    ArrayList<CardViewNotas> elementos = new ArrayList<CardViewNotas>();
    RequestQueue requestQueue;
    AdaptadorNotas adaptadorNotas;


    String email;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        adaptadorNotas = new AdaptadorNotas(elementos,getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_notas, container, false);

        email = "victor@gmail.com";


        dameNotas(email,elementos);
        init();
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
    private void dameNotas (String email, ArrayList<CardViewNotas>notas){

        String URL ="http://10.241.43.234/xrv/notas.php?email=" + email;


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i< jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String ntAsignatura = jsonObject.getString("asignatura");
                                String ntNota = jsonObject.getString("nota");
                                notas.add(new CardViewNotas(ntAsignatura, ntNota));
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    /*private void dameNotas (String email,  ArrayList<CardViewNotas> elementos){

        String URL ="http://10.241.43.234/xrv/notas.php?email=" + email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length()>0){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i <jsonArray.length(); i++){
                            JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                            String ntAsignatura = jsonObject.getString("asignatura");
                            String ntNota = jsonObject.getString("nota");
                            elementos.add(new CardViewNotas(ntAsignatura,ntNota));
                            //adaptadorNotas.notifyItemRangeChanged(elementos.size(),1);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);



    }*/


}