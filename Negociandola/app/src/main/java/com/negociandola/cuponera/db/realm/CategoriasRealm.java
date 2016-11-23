package com.negociandola.cuponera.db.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lapprestamo on 02/11/16.
 */

public class CategoriasRealm extends RealmObject {

    @PrimaryKey
    private int id;

    private RealmList<CategoriaRealm> categorias;

    public RealmList<CategoriaRealm> getCategorias() {
        return categorias;
    }

    public void setCategorias(RealmList<CategoriaRealm> categorias) {
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
