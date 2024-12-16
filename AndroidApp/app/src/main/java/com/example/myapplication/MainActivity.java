package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText documento, nombre, fecha, correo, telefono, latitud, longitud;
    private Spinner estado;
    private RadioButton femenino, masculino;
    private Button guardar, consultar;

    private RequestQueue requestQueue;
    private static final String URL_SERVIDOR = "http://10.0.2.2/empresa/Insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        documento = findViewById(R.id.documento);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        correo = findViewById(R.id.correo);
        telefono = findViewById(R.id.telefono);
        latitud = findViewById(R.id.latitud);
        longitud = findViewById(R.id.longitud);
        estado = findViewById(R.id.estado);
        masculino = findViewById(R.id.masculino);
        femenino = findViewById(R.id.femenino);
        guardar = findViewById(R.id.guardar);
        consultar = findViewById(R.id.consultar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente(v);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void guardarCliente(View view) {
        if (documento.getText().toString().isEmpty() ||
                nombre.getText().toString().isEmpty() ||
                fecha.getText().toString().isEmpty() ||
                correo.getText().toString().isEmpty() ||
                telefono.getText().toString().isEmpty() ||
                latitud.getText().toString().isEmpty() ||
                longitud.getText().toString().isEmpty() ||
                estado.getSelectedItem() == null ||
                (!femenino.isChecked() && !masculino.isChecked())) {

            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


        String sexo = femenino.isChecked() ? "Femenino" : "Masculino";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_SERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error al registrar cliente";
                        if (error.networkResponse != null) {
                            errorMessage += ": " + new String(error.networkResponse.data);
                        } else if (error.getMessage() != null) {
                            errorMessage += ": " + error.getMessage();
                        }
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("documento", documento.getText().toString());
                params.put("nombre", nombre.getText().toString());
                params.put("fecha", fecha.getText().toString());
                params.put("correo", correo.getText().toString());
                params.put("telefono", telefono.getText().toString());
                params.put("latitud", latitud.getText().toString());
                params.put("longitud", longitud.getText().toString());
                params.put("estado", estado.getSelectedItem().toString());
                params.put("sexo", sexo);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
