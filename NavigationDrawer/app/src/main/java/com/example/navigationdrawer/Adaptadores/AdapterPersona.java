package com.example.navigationdrawer.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.Entidades.Persona;
import com.example.navigationdrawer.R;

import java.util.ArrayList;

public class AdapterPersona extends RecyclerView.Adapter<AdapterPersona.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Persona> model;

    // listener
    private  View.OnClickListener listener;

    public AdapterPersona(Context context, ArrayList<Persona> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_personas, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickLister(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre();
        String fechanacimiento = model.get(position).getFechanacimiento();
        int imagen = model.get(position).getImagenid();

        holder.nombres.setText(nombre);
        holder.fechanacimiento.setText(fechanacimiento);
        holder.imagen.setImageResource(imagen);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombres, fechanacimiento;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.nombre_persona);
            fechanacimiento = itemView.findViewById(R.id.fecha_nacimiento);
            imagen = itemView.findViewById(R.id.imagen_persona);

        }
    }
}
