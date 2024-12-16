package com.example.envioinfor;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Guardar extends AppCompatActivity {
    EditText txt_nombres;
    TextView txt_resumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        txt_nombres = (EditText) findViewById(R.id.txt_nombres);
        txt_resumen = (TextView) findViewById(R.id.txt_resumen);

        // se crea los nombres de las variables con respectivos tipos de datos

        String correo = getIntent().getStringExtra("txt_correo");
        String comentarios = getIntent().getStringExtra("txt_comentarios");
        boolean horario = getIntent().getBooleanExtra(" rb_completo",false);


        //Determino el horarios
        String jornada = horario ? "Completo":"Medio";

        // Creo la Data
        String resultados = "Email: "+ correo + "\nComentarios: "+ comentarios + "\nHorario: "+ jornada;

        txt_nombres.setText(getIntent().getStringExtra("txt_nombres"));
        txt_resumen.setText(resultados);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}