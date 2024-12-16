package com.example.appi22;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GuardarActivity extends AppCompatActivity {
    EditText txt_nombres;
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guardar);

        txt_nombres =(EditText) findViewById(R.id.txt_nombres);

        textView6 = (TextView) findViewById(R.id.textView6);

        // Retrieve the data from the Intent

        String correo = getIntent().getStringExtra("txt_correo");
        String comentarios = getIntent().getStringExtra("txt_comentarios");
        boolean isMasculino = getIntent().getBooleanExtra("rb_masculino", false);

        // Determine the gender string
        String gender = isMasculino ? "Masculino" : "Femenino";

        // Create the data string
        String datos = "Email: " + correo + "\nComentarios: " + comentarios + "\nGénero: " + gender;
        txt_nombres.setText(getIntent().getStringExtra("txt_nombres"));
        textView6.setText(datos);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}