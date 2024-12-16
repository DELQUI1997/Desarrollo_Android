package com.example.etapadevida;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_procesar, btn_limpiar;
    TextView txt_nombre, txt_edad, txt_nombre1, txt_edad1, txt_usted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Se Inicializan los TexView
        txt_nombre = (TextView) findViewById(R.id.txt_nombre);
        txt_edad =(TextView) findViewById(R.id.txt_edad);
        txt_nombre1 =(TextView) findViewById(R.id.txt_nombre1);
        txt_edad1 = (TextView) findViewById(R.id.txt_edad1);
        txt_usted =(TextView) findViewById(R.id.txt_usted);

        // Se Inicializan los Button - Procesar
        btn_procesar =(Button) findViewById(R.id.btn_procesar);
        btn_procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Strind se define la variable text _ nombreApellidos
                String nombresApellidos = txt_nombre.getText().toString();
                txt_nombre1.setText("Nombre: "+nombresApellidos); //Se llama a la variable creada

                // int - defino la variable como entero
                int edad = Integer.parseInt(txt_edad.getText().toString());
                txt_edad1.setText("Su edad es: " + edad +"  Años de edad");

                //Defino las condicionales

                if(edad<=5)
                    txt_usted.setText(" Usted es un Infante por ser menor de 5 años ...!");

                else if (edad>=6 && edad<=12)
                    txt_usted.setText("Usted es un Niño por ser mayor de 5 años y menor de 12 años...!");

                else if (edad>=13 && edad<=17)
                    txt_usted.setText("Usted es un Adolescente por ser mayor de 13 años y menor de 17 años...!");

                else if (edad>=18 && edad<=60)
                    txt_usted.setText("Usted es un Adulto por ser mayor de 18 años y menor de 60 años...!");

                if (edad>61)
                    txt_usted.setText("Usted es un Adulto Mayor");

            }
        });
        btn_limpiar =(Button) findViewById(R.id.btn_limpiar);
        btn_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_usted.setText("");
                txt_edad.setText("");
                txt_nombre1.setText("");
                txt_nombre.setText("");
                txt_edad1.setText("");
                txt_nombre.requestFocus();

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}