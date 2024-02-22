package com.example.xrv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText claveReg;
    String clave;
    EditText emailReg;
    EditText nombreReg;
    EditText apellido1Reg;
    EditText apellido2Reg;
    EditText contrasenaReg;
    Button boton;
    RequestQueue requestQueue;
    private static final String URL1 = "http://192.168.59.41/xrv/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        clave = "12345";
        registraId();
        requestQueue = Volley.newRequestQueue(this);
        boton.setOnClickListener(this);


    }

    private void registraId (){
        emailReg=(EditText) findViewById(R.id.emailReg);
        nombreReg=(EditText) findViewById(R.id.nombreReg);
        apellido1Reg=(EditText) findViewById(R.id.apellido1);
        apellido2Reg=(EditText) findViewById(R.id.apellido2);
        contrasenaReg=(EditText) findViewById(R.id.Password);
        boton = (Button) findViewById(R.id.registrar);
        claveReg=(EditText) findViewById(R.id.ClaveXRV);
    }



    public void onClick (View v){

        if(claveReg.getText().toString().equals(clave)) {
            String email = emailReg.getText().toString().trim();
            String nombre = nombreReg.getText().toString().trim();
            String apellido1 = apellido1Reg.getText().toString().trim();
            String apellido2 = apellido2Reg.getText().toString().trim();
            String contrasena = contrasenaReg.getText().toString().trim();

            crearUsuario(email, nombre, apellido1, apellido2, contrasena);
        }else {
            Toast.makeText(this, "Clave XRV incorrecta", Toast.LENGTH_SHORT).show();
        }

    }

    private void crearUsuario(final String email, final String nombre, final String apellido1,
                              final String apellido2, final String contrasena) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Registro.this, "Te has registrado correctamente", Toast.LENGTH_SHORT).show();
                        volverInicio();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Registro.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                params.put("nombre", nombre);
                params.put("apellido1", apellido1);
                params.put("apellido2", apellido2);
                params.put("contrasena", contrasena);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void volverInicio (){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    };
}