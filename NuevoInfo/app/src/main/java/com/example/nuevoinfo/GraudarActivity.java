package com.example.nuevoinfo;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GraudarActivity extends AppCompatActivity {
    EditText txt_nombres;
    TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graudar);

        txt_nombres = (EditText) findViewById(R.id.txt_nombres);
        textView3 = (TextView) findViewById(R.id.textView3);

        // se crea los nombres de las variables con respectivos tipos de datos

        String correo = getIntent().getStringExtra("txt_correo");
        String comentarios = getIntent().getStringExtra("txt_comentarios");
        boolean horario = getIntent().getBooleanExtra("rb_completo",false);
        boolean propiedad = getIntent().getBooleanExtra("cb_casa",false);
        String listaSeleccionada = getIntent().getStringExtra("cbx_lista");


        //Determino el horarios
        String jornada = horario ? "Tiempo Completo":"Medio Tiempo";
        String propiedades = propiedad ? "Casa Propia":"Auto Propio";

        // Creo la Data
        String resultados = "Email:  "+ correo + "\nDireccion:  "+ comentarios +
                            "\nHorario:  "+ jornada + "\nPropiedades:  "+ propiedades + "\nCarrera:  "+ listaSeleccionada;

        txt_nombres.setText(getIntent().getStringExtra("txt_nombres"));
        textView3.setText(resultados);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}