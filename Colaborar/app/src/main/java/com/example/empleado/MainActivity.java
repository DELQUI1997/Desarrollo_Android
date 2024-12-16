package com.example.empleado;

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
    TextView txtEmpleado, txtHoras, txtTarifa, txtTrabajador, txtTrabajadas, txtTarifa2, txtBruto, txtEssalud,txtAfb,txtNeto;
    Button btnProcesar, btnLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtEmpleado = (TextView) findViewById(R.id.txtEmpleado);
        txtHoras = (TextView) findViewById(R.id.txtHoras);
        txtTarifa = (TextView) findViewById(R.id.txtTarifa);
        txtTrabajador = (TextView) findViewById(R.id.txtTrabajador);
        txtTrabajadas = (TextView)  findViewById(R.id.txtTrabajadas);
        txtTarifa2 =(TextView) findViewById(R.id.txtTafifa2);
        txtBruto = (TextView) findViewById(R.id.txtBruto);
        txtEssalud = (TextView) findViewById(R.id.txtEssalud);
        txtAfb = (TextView) findViewById(R.id.txtAfp);
        txtNeto = (TextView) findViewById(R.id.txtNeto);


        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnProcesar = (Button) findViewById(R.id.btnProcesar);
        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtEmpleado.getText().toString();
                int horas = Integer.parseInt(txtHoras.getText().toString());
                int tarif = Integer.parseInt(txtTarifa.getText().toString());

                txtTrabajador.setText("" + name);
                txtTrabajadas.setText("" + horas);
                txtTarifa2.setText("" + tarif);

                int bru1 = horas * tarif;
                txtBruto.setText(String.valueOf(bru1));


                double essalud = bru1 * 0.12;
                double afp = bru1 * 0.10;
                double neto = bru1 - essalud - afp;

                txtEssalud.setText(String.format("%.2f", essalud));
                txtAfb.setText(String.format("%.2f", afp));
                txtNeto.setText(String.format("%.2f", neto));

            }


        });
            btnLimpiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtEmpleado.setText("");
                    txtHoras.setText("");
                    txtTarifa.setText("");
                    txtTrabajador.setText("");
                    txtTrabajadas.setText("");
                    txtTarifa2.setText("");
                    txtBruto.setText("");
                    txtEssalud.setText("");
                    txtAfb.setText("");
                    txtNeto.setText("");
                }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}