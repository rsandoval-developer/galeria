package com.negociandola.cuponera.db.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lapprestamo on 28/10/16.
 */

public class CategoriaRealm extends RealmObject{

    @PrimaryKey
    private int id;

    private String nombreCategoria;

    private RealmList<CuponRealm> cupones;

    public RealmList<CuponRealm> getCupones() {
        return cupones;
    }

    public void setCupones(RealmList<CuponRealm> cupones) {
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
