package com.example.navigationdrawer.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.Adaptadores.AdapterPersona;
import com.example.navigationdrawer.Entidades.Persona;
import com.example.navigationdrawer.R;

import java.util.ArrayList;

public class FragmentPersonas extends Fragment {
    AdapterPersona adapterPersona;
    RecyclerView recyclerView;
    ArrayList<Persona> listaPersonas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personas_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        listaPersonas = new ArrayList<>();
        // Cargar la lista

        cargarLista();


        //Mostrar Datos

        mostrarData();

        return view;
    }
    public void cargarLista(){
        listaPersonas.add(new Persona("Gohan","1997-05-07",R.drawable.image1));
        listaPersonas.add(new Persona("Goku","1997-05-07",R.drawable.image2));
        listaPersonas.add(new Persona("Goten","1997-05-07",R.drawable.image3));
        listaPersonas.add(new Persona("Picoro","1997-05-07",R.drawable.image4));
        listaPersonas.add(new Persona("Vegueta","1997-05-07",R.drawable.image5));
        listaPersonas.add(new Persona("Dragon","1997-05-07",R.drawable.image6));
        listaPersonas.add(new Persona("Spiderman","1997-05-07",R.drawable.image7));
        listaPersonas.add(new Persona("Spiderman 2","1997-05-07",R.drawable.image8));

    }

    public void mostrarData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersona = new AdapterPersona (getContext(), listaPersonas);
        recyclerView.setAdapter(adapterPersona);

        adapterPersona.setOnClickLister(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = listaPersonas.get(recyclerView.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(),"selecciono: "+ nombre, Toast.LENGTH_LONG).show();

            }
        });
    }
}
