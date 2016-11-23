package com.negociandola.cuponera.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.negociandola.cuponera.R;
import com.negociandola.cuponera.adapter.CuponAdaptador;
import com.negociandola.cuponera.db.Categoria;
import com.negociandola.cuponera.db.Cupon;
import com.negociandola.cuponera.db.realm.RealmDataService;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriaFragment extends Fragment {

    private final static String ARG_CATEGORIA = "categoria";
    private List<Cupon> cupones = new ArrayList<>();
    private RecyclerView listaCupones;
    private CuponAdaptador adaptador;
    private RealmDataService realmDataService;

    public static CategoriaFragment newInstance(String categoria) {
        CategoriaFragment fragment = new CategoriaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_CATEGORIA, categoria);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bares_antros, container, false);
        Gson gson = new Gson();
        if (getArguments() != null) {
            cupones = gson.fromJson(getArguments().getString(ARG_CATEGORIA), Categoria.class).getCupones();
        }
        realmDataService = new RealmDataService();
        listaCupones = (RecyclerView) v.findViewById(R.id.rvBaresAntros);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaCupones.setLayoutManager(linearLayoutManager);
        adaptador = new CuponAdaptador(cupones, getActivity());
        listaCupones.setAdapter(adaptador);
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        adaptador.notifyDataSetChanged();

    }
}


