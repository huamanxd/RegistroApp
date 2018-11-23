package com.systemtesis.multiserviceelectronicspc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity {

    /* Ingresar todos los Valores DE ENTRADA */
    EditText txt1,txt2;
    Button btningresar,btnregistrar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        /* Instanciar todos los valores DE ENTRADA */
        txt1=(EditText)findViewById(R.id.txt1);
        txt2=(EditText)findViewById(R.id.txt2);
        btningresar=(Button)findViewById(R.id.btningresar);
        btnregistrar=(Button)findViewById(R.id.btnregistrar);
        /* Validar el boton Onclick del boton ingresar */
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("https://hchristian6.000webhostapp.com/BUSCAR_PRODUCTO.php?usuario="+txt1.getText()+"&clave="+txt2.getText()+"");
            }
        });
        /* Validar el boton Onclick del boton registrar */
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, Registro.class);
                startActivity(intent);
            }
        });




    }

    private void ejecutarServicio(String URL){
         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
             @Override
             public void onResponse(JSONArray response) {
                 JSONObject jsonObject = null;
                 for (int i = 0; i < response.length(); i++) {
                     Intent intent = new Intent(Principal.this, Menu.class);
                     startActivity(intent);
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(),"Error de Conexion",Toast.LENGTH_SHORT).show();
             }
         }
         );
         requestQueue=Volley.newRequestQueue(this);
         requestQueue.add(jsonArrayRequest);

    }


}
