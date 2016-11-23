package com.negociandola.cuponera.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.negociandola.cuponera.db.Cupon;
import com.negociandola.cuponera.DetalleCupon;
import com.negociandola.cuponera.R;

import java.util.List;

/**
 * Created by ignacio on 04/07/16.
 */
public class CuponAdaptador extends RecyclerView.Adapter<CuponAdaptador.CuponViewHolder>{

    List<Cupon> cupones;
    private Activity context;

    public CuponAdaptador(List<Cupon> cupones, Activity context) {
        this.cupones = cupones;
        this.context = context;
    }


    @Override
    public CuponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lista_cupones,parent,false);
        return new CuponViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CuponViewHolder cuponViewHolder, int position) {
        final Cupon cupon = cupones.get(position);
        Glide.with(context)
                .load(cupon.getImagenCupon())
                .centerCrop()
                .into(cuponViewHolder.imgImagenCupon);
        cuponViewHolder.tituloCupon.setText(cupon.getTituloCupon());
        cuponViewHolder.textNombreNegocio.setText(cupon.getNombreNegocio());

        cuponViewHolder.cardview_cupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetalleCupon.class);
                intent.putExtra("titulo_cupon",cupon.getTituloCupon());
                intent.putExtra("imagen_cupon",cupon.getImagenCupon());
                intent.putExtra("vecimiento",cupon.getVencimiento());
                intent.putExtra("folio",cupon.getFolio());
                intent.putExtra("restriccion",cupon.getRestricciones());
                intent.putExtra("nombre_negocio",cupon.getNombreNegocio());
                intent.putExtra("descripcion_negocio",cupon.getDescripcionNegocio());
                intent.putExtra("horario_negocio",cupon.getHorarioNegocio());
                intent.putExtra("direccion_negocio",cupon.getDireccionNegocio());
                intent.putExtra("telefono_negocio",cupon.getTelefonoNegocio());
                intent.putExtra("is_canjeado",cupon.isCanjeado());
                intent.putExtra("is_aprobado",cupon.isAprobado());
                intent.putExtra("id",cupon.getId());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(600);
                    context.getWindow().setExitTransition(explode);
                    context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(context,view, context.getString(R.string.imgImagenCupon)).toBundle());
                }else {
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return cupones.size();
    }



    public static class CuponViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgImagenCupon;
        private TextView tituloCupon;
        private TextView textNombreNegocio;
        private LinearLayout cardview_cupon;

        public CuponViewHolder(View itemView) {
            super(itemView);
            imgImagenCupon = (ImageView) itemView.findViewById(R.id.imgImagenCupon);
            tituloCupon = (TextView) itemView.findViewById(R.id.tituloCupon);
            textNombreNegocio = (TextView) itemView.findViewById(R.id.textNombreNegocio);
            cardview_cupon = (LinearLayout) itemView.findViewById(R.id.cardview_cupon);
        }
    }
}
