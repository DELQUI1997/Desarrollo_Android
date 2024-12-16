package com.example.imc_clase6;

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
    TextView id_txtpeso, id_txtaltura, id_txtdescripcion, id_txtimc;

    Button btn_calcular, btn_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        id_txtpeso = (TextView) findViewById(R.id.id_txtpeso);
        id_txtaltura = (TextView) findViewById(R.id.id_txtaltura);
        id_txtdescripcion =(TextView) findViewById(R.id.id_txtdescripcion);
        id_txtimc =(TextView) findViewById(R.id.id_txtimc);

        btn_calcular = (Button)findViewById(R.id.btn_calcular);
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double peso=Double.parseDouble(id_txtpeso.getText().toString());
                double altura=Double.parseDouble(id_txtaltura.getText().toString());
                double imc=peso/(altura * altura);

                id_txtimc.setText("IMC:  "+String.format("%.2f", imc));

                // se agrega las condiciones

                if(imc<=18)
                    id_txtdescripcion.setText("Desacripcion: Bajo de peso");
                else if (imc>=19 && imc<=25)
                    id_txtdescripcion.setText("Descripcion: Su peso es normal");
                else if (imc>=26 && imc<=30)
                    id_txtdescripcion.setText("Desacripcion: Sobre peso");
                else
                    id_txtdescripcion.setText("Descripcion: Cuidado con su salud");

            }
        });
        btn_eliminar =(Button)findViewById(R.id.btn_eliminar);
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_txtdescripcion.setText("Descripcion: ");
                id_txtimc.setText("IMC: ");
                id_txtpeso.setText("");
                id_txtaltura.setText("");

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}