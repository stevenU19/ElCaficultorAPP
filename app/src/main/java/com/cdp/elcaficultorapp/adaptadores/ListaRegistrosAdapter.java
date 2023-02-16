package com.cdp.elcaficultorapp.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.elcaficultorapp.R;
import com.cdp.elcaficultorapp.RegistroActivity;
import com.cdp.elcaficultorapp.entidades.Registro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaRegistrosAdapter extends RecyclerView.Adapter<ListaRegistrosAdapter.RegistroViewHolder> {

    ArrayList<Registro> listaRegistros;
    ArrayList<Registro> listaOriginal;

    public ListaRegistrosAdapter(ArrayList<Registro> listaRegistros) {
        this.listaRegistros = listaRegistros;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaRegistros);
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_registro, null, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.viewFechaInicio.setText(listaRegistros.get(position).getFechaInicio());
        holder.viewFechaFinal.setText(listaRegistros.get(position).getFechaFinal());
        //holder.viewPesoRecoleccion.setText(listaRegistros.get(position).getPesoRecoleccion());
        //holder.viewEstadoCafe.setText(listaRegistros.get(position).getEstadoCafe());
        //holder.viewFechaVenta.setText(listaRegistros.get(position).getFechaVenta());
        //holder.viewPesoVenta.setText(listaRegistros.get(position).getPesoVenta());
        //holder.viewPrecioKG.setText(listaRegistros.get(position).getPrecioKG());
    }


    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaRegistros.clear();
            listaRegistros.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Registro> collecion = listaRegistros.stream()
                        .filter(i -> i.getFechaVenta().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaRegistros.clear();
                listaRegistros.addAll(collecion);
            } else {
                for (Registro c : listaOriginal) {
                    if (c.getFechaVenta().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaRegistros.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder {
        TextView viewFechaInicio, viewFechaFinal, viewPesoRecoleccion, viewEstadoCafe, viewFechaVenta, viewPesoVenta, viewPrecioKG;
        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            viewFechaInicio = itemView.findViewById(R.id.viewFechaInicio);
            viewFechaFinal = itemView.findViewById(R.id.viewFechaFinal);
            viewPesoRecoleccion = itemView.findViewById(R.id.viewPesoRecoleccion);
            viewEstadoCafe = itemView.findViewById(R.id.viewEstadoCafe);
            viewFechaVenta = itemView.findViewById(R.id.viewFechaVenta);
            viewPesoVenta = itemView.findViewById(R.id.viewPesoVenta);
            viewPrecioKG = itemView.findViewById(R.id.viewPrecioKG);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RegistroActivity.class);
                    intent.putExtra("ID", listaRegistros.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
