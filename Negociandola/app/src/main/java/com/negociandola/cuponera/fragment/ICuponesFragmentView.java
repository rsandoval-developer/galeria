package com.negociandola.cuponera.fragment;

import com.negociandola.cuponera.adapter.CuponAdaptador;
import com.negociandola.cuponera.db.Cupon;

import java.util.List;

/**
 * Created by ignacio on 06/07/16.
 */
public interface ICuponesFragmentView {

    public void generarLinearLayoutVertical();

    public CuponAdaptador crearAdaptador(List<Cupon> cupones);

    public void inicializarAdaptadorRV(CuponAdaptador adaptador);

}
