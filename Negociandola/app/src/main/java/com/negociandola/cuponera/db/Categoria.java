package com.negociandola.cuponera.db;

import java.util.List;

/**
 * Created by lapprestamo on 28/10/16.
 */

public class Categoria{

    private int id;

    private String nombreCategoria;

    private List<Cupon> cupones;

    public List<Cupon> getCupones() {
        return cupones;
    }

    public void setCupones(List<Cupon> cupones) {
        this.cupones = cupones;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
