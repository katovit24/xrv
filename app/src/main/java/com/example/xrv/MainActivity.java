package com.example.xrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView registro;
    EditText email, password;
    Button boton;
    String URL = "http://10.241.43.234/xrv/validar_usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifica();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarUsuario(URL);
            }
        });

     /*   registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrar();
            }
        });*/

    }



    private void identifica (){
        //registro = findViewById(R.id.registrate);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        boton = findViewById(R.id.botonEntrar);
    }
    private void registrar (){
        Intent registrar = new Intent(this,Registro.class);
        startActivity(registrar);
    }
    private void acceso (){
        Intent acceder = new Intent(this, Principal.class);
        acceder.putExtra("email", email.getText().toString());
        startActivity(acceder);
        finish();
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    /*if (jsonArray.length() > 0) {
                        JSONObject jsonObjectUsuario = jsonArray.getJSONObject(0);
                        Intent intent =new Intent(getApplicationContext(), registro.class);
                        startActivity(intent);
                    }*/
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObjectUsuario = jsonArray.getJSONObject(0);
                        Intent intent = new Intent(getApplicationContext(), Principal.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }


            }
        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("email",email.getText().toString());
                parametros.put("contrasena",password.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}