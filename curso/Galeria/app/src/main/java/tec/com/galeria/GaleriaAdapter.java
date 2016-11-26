package tec.com.galeria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lapprestamo on 19/11/16.
 */

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.ViewHolder> {

    private Context context;
    private List<GaleriaModel.Imagen> galeria;


    public GaleriaAdapter(Context context, List<GaleriaModel.Imagen> galeria) {
        this.context = context;
        this.galeria = galeria;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_galeria, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GaleriaModel.Imagen imagen = galeria.get(position);
        Glide.with(context)
                .load(imagen.getUrl())
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return galeria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
        }
    }
}
