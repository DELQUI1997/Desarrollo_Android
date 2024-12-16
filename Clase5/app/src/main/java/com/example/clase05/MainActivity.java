package com.example.clase05;

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
    CheckBox id_cbmouse, id_cbteclado, id_cbparlantes, id_cbaudifonos;
    RadioButton id_laptops, id_pc;

    Button id_enviar, id_limpiar;

    TextView id_txtresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        id_cbmouse=(CheckBox) findViewById(R.id.id_cbmouse);
        id_cbteclado=(CheckBox) findViewById(R.id.id_cbteclado);
        id_cbparlantes=(CheckBox) findViewById(R.id.id_cbparlantes);
        id_cbaudifonos=(CheckBox) findViewById(R.id.id_cbaudifonos);
        id_laptops =(RadioButton) findViewById(R.id.id_laptops) ;
        id_pc =(RadioButton) findViewById(R.id.id_pc) ;
        id_txtresultado = (TextView) findViewById(R.id.id_txtresultado);
        id_enviar = (Button) findViewById(R.id.id_enviar);
        id_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double total=0.0;
                if(id_pc.isChecked())
                    total=total+10;

                if(id_cbmouse.isChecked())
                    total=total+10;

                if(id_cbteclado.isChecked())
                    total=total+20;

                if(id_cbaudifonos.isChecked())
                    total=total+30;

                if(id_cbparlantes.isChecked())
                    total=total+40;

                id_txtresultado.setText("Precio: S/."+total);
            }
        });
        id_limpiar =(Button)findViewById(R.id.id_limpiar);
        id_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_pc.setChecked(false);
                id_laptops.setChecked(false);
                id_cbparlantes.setChecked(false);
                id_cbaudifonos.setChecked(false);
                id_cbteclado.setChecked(false);
                id_cbmouse.setChecked(false);
                id_txtresultado.setText("");
                id_pc.clearFocus();

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}