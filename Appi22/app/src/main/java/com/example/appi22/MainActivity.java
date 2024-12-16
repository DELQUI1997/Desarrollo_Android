package com.example.appi22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    RadioButton rb_masculino, rb_femenino;
    Spinner cbx_distrito;
    Button btn_enviar, btn_limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txt_nombres = (EditText) findViewById(R.id.txt_nombres);
        txt_correo = (EditText) findViewById(R.id.txt_correo);
        txt_comentarios = (EditText) findViewById(R.id.txt_comentarios);



        rb_masculino =(RadioButton) findViewById(R.id.rb_masculino);
        rb_femenino =(RadioButton) findViewById(R.id.rb_femenino);

        cbx_distrito = (Spinner) findViewById(R.id.cbx_distrito);

        btn_enviar = (Button) findViewById(R.id.btn_enviar);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_nombres.getText().toString().equals("")|| txt_correo.getText().toString().equals("")
                        || (!rb_masculino.isChecked() && !rb_femenino.isChecked())){
                    Toast.makeText(MainActivity.this, "Debe agregar su nombre",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,GuardarActivity.class);
                    intent.putExtra("txt_nombres", txt_nombres.getText().toString());
                    intent.putExtra("txt_correo", txt_correo.getText().toString());
                    intent.putExtra("txt_comentarios", txt_comentarios.getText().toString());
                    intent.putExtra("rb_masculino", rb_masculino.isChecked());
                    intent.putExtra("rb_femenino", rb_femenino.isChecked());



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