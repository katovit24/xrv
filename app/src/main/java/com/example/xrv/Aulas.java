package com.example.xrv;

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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//Clase para devolver al usuario el recyclerview con los datos recibidos en la petición al servidor de la BBDD
public class Aulas extends Fragment {
    View vista;
    RecyclerView recyclerView;
    ArrayList <CardViewAulas> elementos;
    RequestQueue requestQueue;
    AdaptadorAulas adaptadorAulas;
    String email;
    //Método constructor vacío
    public Aulas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Método donde se infla la vista principal del fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_aulas, container, false);
        elementos = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        email = getArguments().getString("mailAulas");
        dameAulas(email,elementos);
        return vista;
    }


    //Método que enlaza los datos obtenidos en el Arraylist con el recyclerview
    public void init(){
        AdaptadorAulas adaptadorAulas = new AdaptadorAulas(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerAulas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorAulas);
    }

    //Método que recibe la respuesta de la petición al servidor a través de la URL devolviendo los datos de las aulas
    private void dameAulas(String email, ArrayList<CardViewAulas> elementos) {
        String URL ="http://192.168.0.133/xrv/aulas.php?email=" + email;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i <response.length(); i++){
                        JSONObject jsonObject = new JSONObject(response.get(i).toString());
                        String asignatura = jsonObject.getString("asignatura").toUpperCase();
                        String aula = jsonObject.getString("aula").toUpperCase();
                        elementos.add(new CardViewAulas(asignatura,aula));
                        Log.i("valorList", "El valor es" + elementos);
                    }
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