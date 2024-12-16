package com.example.formulariov1;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainImprimir extends AppCompatActivity {
    EditText txt_datos, txt_dni;
    TextView textView10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_imprimir);

        txt_datos = (EditText) findViewById(R.id.txt_datos);
        textView10 = (TextView) findViewById(R.id.textView10);


        // Retrieve the data from the Intent
        String dni = getIntent().getStringExtra("txt_dni");
        String telefono = getIntent().getStringExtra("txt_telefono");
        String provincia = getIntent().getStringExtra("txt_provincia");
        String correo = getIntent().getStringExtra("txt_correo");
        boolean estudiosPrimaria = getIntent().getBooleanExtra("cb_primaria", false);
        boolean estudiosSecundaria = getIntent().getBooleanExtra("cb_secundaria", false);
        boolean estudiosTecnico = getIntent().getBooleanExtra("cb_tecnico", false);
        boolean estudiosUniversitario = getIntent().getBooleanExtra("cb_universitario", false);
        boolean estadoSoltero = getIntent().getBooleanExtra("rb_soltero", false);
        boolean estadoCasado = getIntent().getBooleanExtra("rb_casado", false);
        boolean estadoViudo = getIntent().getBooleanExtra("rb_viudo", false);
        boolean estadoDivorciado = getIntent().getBooleanExtra("rb_divorciado", false);

        // Determine the study level and civil status
        String nivelestudio = estudiosPrimaria ? "Primaria" : estudiosSecundaria ? "Secundaria" : estudiosTecnico ? "Tecnico" : estudiosUniversitario ? "Universitario" : "No especificado";
        String civil = estadoSoltero ? "Soltero" : estadoCasado ? "Casado" : estadoViudo ? "Viudo" : estadoDivorciado ? "Divorciado" : "No especificado";

        // Create the data string
        String resultados = "DNI : " + dni + "\nTELEFONO : " + telefono +
                "\nPROVINCIA : " + provincia + "\nCORREO : " + correo + "\nESTUDIOS : " + nivelestudio + "\nESTADO CIVIL : " + civil;

        txt_datos.setText(getIntent().getStringExtra("txt_datos"));
        textView10.setText(resultados);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}