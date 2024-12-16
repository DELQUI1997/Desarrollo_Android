package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private EditText edtDocumento, edtNombre, edtTelefono, edtCorreo, edtSexo, edtEstado;
    private Button btnConsultar, btnModificar,  regresar;

    private RequestQueue requestQueue;
    private static final String URL_CONSULTAR = "http://10.0.2.2/empresa/Consultar.php";
    private static final String URL_MODIFICAR = "http://10.0.2.2/empresa/Modificar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtDocumento = findViewById(R.id.edt_documento);
        edtNombre = findViewById(R.id.edt_nombre);
        edtTelefono = findViewById(R.id.edt_telefono);
        edtCorreo = findViewById(R.id.edt_correo);
        edtSexo = findViewById(R.id.edt_sexo);
        edtEstado = findViewById(R.id.edt_estado);
        regresar =(Button) findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnConsultar = findViewById(R.id.btn_consultar);
        btnModificar = findViewById(R.id.btn_modificar);

        requestQueue = Volley.newRequestQueue(this);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarCliente();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarCliente();
            }
        });
    }

    private void consultarCliente() {
        String documento = edtDocumento.getText().toString().trim();

        if (documento.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa el número de documento", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = URL_CONSULTAR + "?documento=" + documento;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nombre = response.getString("nombre");
                            String telefono = response.getString("telefono");
                            String correo = response.getString("correo");
                            String sexo = response.getString("sexo");
                            String estado = response.getString("estado");

                            edtNombre.setText(nombre);
                            edtTelefono.setText(telefono);
                            edtCorreo.setText(correo);
                            edtSexo.setText(sexo);
                            edtEstado.setText(estado);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity2.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity2.this, "Error al consultar cliente", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void modificarCliente() {
        String documento = edtDocumento.getText().toString().trim();
        String nombre = edtNombre.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String correo = edtCorreo.getText().toString().trim();
        String sexo = edtSexo.getText().toString().trim();
        String estado = edtEstado.getText().toString().trim();

        if (documento.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || sexo.isEmpty() || estado.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL_MODIFICAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity2.this, "Cliente modificado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error al modificar cliente";
                        if (error.networkResponse != null) {
                            errorMessage += ": " + new String(error.networkResponse.data);
                        } else if (error.getMessage() != null) {
                            errorMessage += ": " + error.getMessage();
                        }
                        Toast.makeText(MainActivity2.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("documento", documento);
                params.put("nombre", nombre);
                params.put("telefono", telefono);
                params.put("correo", correo);
                params.put("sexo", sexo);
                params.put("estado", estado);
                return params;
            }
        };

        requestQueue.add(stringRequest);

        // Configuración de la ActionBar para mostrar el botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Manejo de los Insets de la ventana para ajustar la vista según las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Termina la actividad cuando se presiona el botón de retroceso
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
