package com.negociandola.cuponera.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.negociandola.cuponera.DetalleCupon;
import com.negociandola.cuponera.R;
import com.negociandola.cuponera.db.realm.MisCanjes;

import java.util.List;

/**
 * Created by ignacio on 04/07/16.
 */
public class MisCanjesAdapter extends RecyclerView.Adapter<MisCanjesAdapter.CuponViewHolder> {

    List<MisCanjes> misCanjes;
    private Activity context;

    public MisCanjesAdapter(List<MisCanjes> misCanjes, Activity context) {
        this.misCanjes = misCanjes;
        this.context = context;
    }


    @Override
    public CuponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mis_canjes, parent, false);
        return new CuponViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CuponViewHolder cuponViewHolder, int position) {
        MisCanjes miCanje = misCanjes.get(position);
        cuponViewHolder.negocio.setText("Negocio: "+miCanje.getNombreNegocio());
        cuponViewHolder.cupon.setText(miCanje.getCupon());
        cuponViewHolder.fecha.setText("Fecha de Canje: " + miCanje.getFechaCanje());
        cuponViewHolder.icon.setColorFilter(context.getResources().getColor(R.color.colorPrimaryText));
        cuponViewHolder.view.setOnClickListener((v) -> {
            Intent intent = new Intent(context,DetalleCupon.class);
            intent.putExtra("titulo_cupon",miCanje.getTituloCupon());
            intent.putExtra("imagen_cupon",miCanje.getImagenCupon());
            intent.putExtra("vecimiento",miCanje.getVencimiento());
            intent.putExtra("folio",miCanje.getFolio());
            intent.putExtra("restriccion",miCanje.getRestricciones());
            intent.putExtra("nombre_negocio",miCanje.getNombreNegocio());
            intent.putExtra("descripcion_negocio",miCanje.getDescripcionNegocio());
            intent.putExtra("horario_negocio",miCanje.getHorarioNegocio());
            intent.putExtra("direccion_negocio",miCanje.getDireccionNegocio());
            intent.putExtra("telefono_negocio",miCanje.getTelefonoNegocio());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return misCanjes.size();
    }


    public static class CuponViewHolder extends RecyclerView.ViewHolder {
        private TextView negocio;
        private TextView cupon;
        private TextView fecha;
        private ImageView icon;
        private View view;

        public CuponViewHolder(View itemView) {
            super(itemView);
            negocio = (TextView) itemView.findViewById(R.id.negocio);
            cupon = (TextView) itemView.findViewById(R.id.cupon);
            fecha = (TextView) itemView.findViewById(R.id.fecha_canje);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            view = itemView;
        }
    }
}
