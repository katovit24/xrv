package com.example.xrv;

import android.app.DownloadManager;
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


public class Asignaturas extends Fragment {

    View vista;
    RecyclerView recyclerView;
    ArrayList<CardViewAsignaturas> elementos;
    RequestQueue requestQueue;
    AdaptadorAsignaturas adaptadorAsignaturas;
    String email;

    public Asignaturas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_asignaturas, container, false);
        elementos = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getContext());

        email = getArguments().getString("mailAsignaturas");


        dameAsignarturas(email,elementos);
        return vista;
    }



    public void init(){
        AdaptadorAsignaturas adaptadorAsignaturas = new AdaptadorAsignaturas(elementos,getContext() );
        recyclerView = vista.findViewById(R.id.reciclerAsignaturas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptadorAsignaturas);
    }

    private void dameAsignarturas(String email, ArrayList<CardViewAsignaturas> elementos) {
        //String URL ="http://192.168.1.143/xrv/asignaturas.php?email=" + email;
        String URL ="http://192.168.0.133/xrv/asignaturas.php?email=" + email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0; i <response.length(); i++){
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                String asAsignatura = jsonObject.getString("asignatura").toUpperCase();
                                String asProfesor = jsonObject.getString("nombre").toUpperCase();
                                elementos.add(new CardViewAsignaturas(asAsignatura,asProfesor));

                                Log.i("valorList", "El valor es" + elementos);
                            }
                            adaptadorAsignaturas = new AdaptadorAsignaturas(elementos,getContext());
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