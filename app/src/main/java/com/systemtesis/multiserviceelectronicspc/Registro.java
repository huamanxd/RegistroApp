package com.systemtesis.multiserviceelectronicspc;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    /* Ingresar todos los Valores DE ENTRADA */
    EditText txtr1,txtr2,txtr3;
    Button btnregistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        /* Instanciar todos los valores DE ENTRADA */
        txtr1=(EditText)findViewById(R.id.txtr1);
        txtr2=(EditText)findViewById(R.id.txtr2);
        txtr3=(EditText)findViewById(R.id.txtr3);
        btnregistrarse=(Button)findViewById(R.id.btnregistrarse);

        /* Validar el boton Onclick del boton REGISTRARSE */
        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ejecutarDatos("https://hchristian6.000webhostapp.com/INSERTAR_PRODUCTO.php");
            }
        });

    }

    private void ejecutarDatos(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Operación Exitosa",Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String,String>();
                parametros.put("nombres",txtr1.getText().toString());
                parametros.put("usuario",txtr2.getText().toString());
                parametros.put("clave",txtr3.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
