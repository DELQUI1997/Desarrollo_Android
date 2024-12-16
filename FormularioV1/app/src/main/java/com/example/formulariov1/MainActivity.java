package com.example.formulariov1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txt_datos, txt_dni, txt_telefono, txt_provincia, txt_correo;
    CheckBox cb_primaria, cb_secundaria, cb_tecnico, cb_universitario;
    RadioButton rb_soltero, rb_casado, rb_viudo, rb_divorciado;

    Button btn_enviar, btn_limpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //inicializamos las variables los textos txt
        txt_datos =(EditText) findViewById(R.id.txt_datos);
        txt_dni =(EditText) findViewById(R.id.txt_dni);
        txt_telefono =(EditText) findViewById(R.id.txt_telefono);
        txt_provincia =(EditText) findViewById(R.id.txt_provincia);
        txt_correo =(EditText) findViewById(R.id.txt_correo);

        //inicilaizamos los checkbox
        cb_primaria =(CheckBox) findViewById(R.id.cb_primaria);
        cb_secundaria =(CheckBox) findViewById(R.id.cb_secundaria);
        cb_tecnico =(CheckBox) findViewById(R.id.cb_tecnico);
        cb_universitario =(CheckBox) findViewById(R.id.cb_universitario);

        //inicilizamos los radio button
        rb_soltero =(RadioButton) findViewById(R.id.cb_soltero);
        rb_casado =(RadioButton) findViewById(R.id.cb_casado);
        rb_viudo =(RadioButton) findViewById(R.id.cb_viudo);
        rb_divorciado =(RadioButton) findViewById(R.id.cb_divorciado);

        //inicilizamos el boton enviar
        btn_enviar =(Button) findViewById(R.id.btn_enviar);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_datos.getText().toString().equals("")|| txt_dni.getText().toString().equals("")
                        || txt_telefono.getText().toString().equals("") || txt_provincia.getText().toString().equals("")
                        || txt_correo.getText().toString().equals("")
                        || (!cb_primaria.isChecked() && !cb_secundaria.isChecked() && !cb_tecnico.isChecked() && !cb_universitario.isChecked())
                        || (!rb_soltero.isChecked() && !rb_casado.isChecked() && !rb_viudo.isChecked() && !rb_divorciado.isChecked())){

                    Toast.makeText(MainActivity.this, "Por favor Ingrese sus datos",Toast.LENGTH_LONG).show();
                }else{




                    Intent intent = new Intent(MainActivity.this,MainImprimir.class);
                    intent.putExtra("txt_datos", txt_datos.getText().toString());
                    intent.putExtra("txt_dni", txt_dni.getText().toString());
                    intent.putExtra("txt_telefono", txt_telefono.getText().toString());
                    intent.putExtra("txt_provincia", txt_provincia.getText().toString());
                    intent.putExtra("txt_correo", txt_correo.getText().toString());
                    intent.putExtra("cb_primaria", cb_primaria.isChecked());
                    intent.putExtra("cb_secundaria", cb_secundaria.isChecked());
                    intent.putExtra("cb_tecnico", cb_tecnico.isChecked());
                    intent.putExtra("cb_universitario", cb_universitario.isChecked());
                    intent.putExtra("rb_soltero", rb_soltero.isChecked());
                    intent.putExtra("rb_casado", rb_casado.isChecked());
                    intent.putExtra("rb_viudo", rb_viudo.isChecked());
                    intent.putExtra("rb_divorciado", rb_divorciado.isChecked());

                    startActivity(intent);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}