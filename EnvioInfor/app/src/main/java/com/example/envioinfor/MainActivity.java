package com.example.envioinfor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txt_nombres, txt_correo, txt_comentarios;
    Spinner cbx_lista;
    RadioButton rb_completo, rb_medio;
    CheckBox cb_casa, cb_auto;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txt_nombres = (EditText) findViewById(R.id.txt_nombres);
        txt_correo = (EditText) findViewById(R.id.txt_correo);
        txt_comentarios = (EditText) findViewById(R.id.txt_comentarios);
        cbx_lista = (Spinner) findViewById(R.id.cbx_lista);
        rb_completo =(RadioButton) findViewById(R.id.rb_completo);
        rb_medio = (RadioButton) findViewById(R.id.rb_medio);
        cb_casa =(CheckBox) findViewById(R.id.cb_casa);
        cb_auto = (CheckBox) findViewById(R.id.cb_auto);

        btn_guardar =(Button) findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_nombres.getText().toString().equals("")|| txt_correo.getText().toString().equals("")
                        || (!rb_completo.isChecked() && !rb_medio.isChecked())
                        || (!rb_completo.isChecked() && !rb_medio.isChecked())){

                    Toast.makeText(MainActivity.this, "Por favor Ingrese sus datos",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, Guardar.class);
                    intent.putExtra("txt_nombres", txt_nombres.getText().toString());
                    intent.putExtra("txt_correo", txt_correo.getText().toString());
                    intent.putExtra("txt_comentarios", txt_comentarios.getText().toString());
                    intent.putExtra("rb_completo", rb_completo.isChecked());
                    intent.putExtra("rb_medio", rb_medio.isChecked());
                    intent.putExtra("cb_casa", cb_casa.isChecked());
                    intent.putExtra("cb_auto", cb_auto.isChecked());


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