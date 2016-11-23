package com.negociandola.cuponera.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lapprestamo on 28/10/16.
 */

public class Categorias {


    @SerializedName("categorias")
    private List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
