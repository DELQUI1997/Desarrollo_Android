package com.example.jugueriasac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
CheckBox cb_huevos, cb_chilaquies, cb_continental, cb_hot, cb_refrescos, cb_jugos, cb_malteadas, cb_cafe;

RadioButton rb_llevar, rb_entrega;

TextView txt_pagar;

Button btn_calcular, btn_cancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cb_cafe =(CheckBox) findViewById(R.id.cb_cafe);
        cb_chilaquies =(CheckBox) findViewById(R.id.cb_chilaquies);
        cb_continental =(CheckBox) findViewById(R.id.cb_continental);
        cb_hot = (CheckBox) findViewById(R.id.cb_hot);
        cb_huevos =(CheckBox) findViewById(R.id.cb_huevos);
        cb_jugos = (CheckBox) findViewById(R.id.cb_jugos);
        cb_malteadas=(CheckBox) findViewById(R.id.cb_malteadas);
        cb_refrescos=(CheckBox) findViewById(R.id.cb_refrescos);

        rb_entrega =(RadioButton) findViewById(R.id.rb_entrega);
        rb_llevar =(RadioButton) findViewById(R.id.rb_llevar);

        btn_calcular =(Button) findViewById(R.id.btn_calcular);
        btn_cancelar =(Button) findViewById(R.id.btn_cancelar);

        txt_pagar =(TextView) findViewById(R.id.txt_pagar);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double total = 0.00;
                if(rb_llevar.isChecked())
                    total = total + 10;

                if(cb_huevos.isChecked())
                    total = total + 60;

                if(cb_chilaquies.isChecked())
                    total = total + 70;

                if(cb_continental.isChecked())
                    total = total + 90;

                if(cb_hot.isChecked())
                    total = total + 50;

                if(cb_refrescos.isChecked())
                    total = total + 15;

                if(cb_jugos.isChecked())
                    total = total + 20;

                if(cb_malteadas.isChecked())
                    total = total + 30;

                if(cb_cafe.isChecked())
                    total = total + 14;

                txt_pagar.setText("Total a Pagar: S/."+total);


            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_llevar.setChecked(false);
                cb_cafe.setChecked(false);
                cb_malteadas.setChecked(false);
                cb_jugos.setChecked(false);
                cb_hot.setChecked(false);
                cb_continental.setChecked(false);
                cb_chilaquies.setChecked(false);
                cb_huevos.setChecked(false);
                txt_pagar.setText("");
                rb_llevar.clearFocus();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}