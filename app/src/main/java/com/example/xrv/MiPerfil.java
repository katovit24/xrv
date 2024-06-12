package com.example.xrv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//Clase que establece los datos de la ventana de perfil
public class MiPerfil extends Fragment {
    RequestQueue requestQueue;
    TextView nombre, apellido, ciclo, mailPerfil;
    String email;
    public MiPerfil() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //Método que inlfa la vista y a partir del @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mi_perfil, container, false);
        requestQueue = Volley.newRequestQueue(getContext());
        identifica(vista);
        Bundle bundle = getArguments();
        if (bundle!=null){
            email = getArguments().getString("mailPerfil");
        }
        Log.i("el mail del perfil", "mail de perfil: " + bundle);
        dameDatosPersonales(email);
        return vista;
    }
    //Método que identifica las vistas
    private void identifica (View vista){
        nombre = vista.findViewById(R.id.perfilNom);
        apellido = vista.findViewById(R.id.perfilApe1);
        ciclo = vista.findViewById(R.id.perfilApe2);
        mailPerfil = vista.findViewById(R.id.perfilEmail);
    }
    //Método que devuelve los datos personales del usuario que accede
    private void dameDatosPersonales(String email){
        //String URL ="http://192.168.1.143/xrv/datosPersonales.php?email=" + email;
        String URL ="http://192.168.0.133/xrv/datosPersonales.php?email=" + email;

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
                            String perApellido1 = jsonObject.getString("apellido");
                            String perCiclo = jsonObject.getString("ciclo");
                            String perMailPerfil = jsonObject.getString("email");
                            nombre.setText(perNombre);
                            apellido.setText(perApellido1);
                            ciclo.setText(perCiclo);
                            mailPerfil.setText(perMailPerfil);
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
}