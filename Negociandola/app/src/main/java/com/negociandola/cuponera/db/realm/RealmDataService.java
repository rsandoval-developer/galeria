package com.negociandola.cuponera.db.realm;

import com.negociandola.cuponera.db.Categoria;
import com.negociandola.cuponera.db.Cupon;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by lapprestamo on 28/10/16.
 */

public class RealmDataService {

    private static RealmDataService sInstance;

    public RealmDataService() {
        super();
    }

    public static RealmDataService newInstance() {
        if (sInstance == null) {
            sInstance = new RealmDataService();
        }
        return sInstance;
    }

    public void saveCategorias(List<Categoria> categorias){
        CategoriasRealm categoriasRealm = new CategoriasRealm();
         RealmList<CategoriaRealm> categoriaList= new RealmList<>();
        for (Categoria categoria : categorias){
            CategoriaRealm categoriaRealm = new CategoriaRealm();
            categoriaRealm.setId(categoria.getId());
            categoriaRealm.setNombreCategoria(categoria.getNombreCategoria());
            RealmList<CuponRealm> cupones = new RealmList<>();
            for (Cupon cupon : categoria.getCupones()){
                CuponRealm cuponRealm = new CuponRealm();
                cuponRealm.setId(cupon.getId());
                cuponRealm.setTituloCupon(cupon.getTituloCupon());
                cuponRealm.setImagenCupon(cupon.getImagenCupon());
                cuponRealm.setNombreNegocio(cupon.getNombreNegocio());
                cuponRealm.setVencimiento(cupon.getVencimiento());
                cuponRealm.setFolio(cupon.getFolio());
                cuponRealm.setRestricciones(cupon.getRestricciones());
                cuponRealm.setDescripcionNegocio(cupon.getDescripcionNegocio());
                cuponRealm.setHorarioNegocio(cupon.getHorarioNegocio());
                cuponRealm.setHorarioNegocio(cupon.getHorarioNegocio());
                cuponRealm.setDireccionNegocio(cupon.getDireccionNegocio());
                cuponRealm.setTelefonoNegocio(cupon.getTelefonoNegocio());
                cuponRealm.setCanjeado(cupon.isCanjeado());
                cuponRealm.setAprobado(cupon.isAprobado());
                cupones.add(cuponRealm);
            }
            categoriaRealm.setCupones(cupones);
            categoriaList.add(categoriaRealm);

        }
        categoriasRealm.setId(1);
        categoriasRealm.setCategorias(categoriaList);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(CategoriasRealm.class);
        realm.copyToRealmOrUpdate(categoriasRealm);
        realm.commitTransaction();
        realm.close();


    }

    public CategoriasRealm getCategorias() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CategoriasRealm> result = realm.where(CategoriasRealm.class).findAll();
        return realm.copyToRealm(result).get(0);

    }


    public void saveMisCanjes(MisCanjes misCanjes) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(misCanjes);
        realm.commitTransaction();
        realm.close();
    }

    public List<MisCanjes> getMisCanjes() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MisCanjes> result = realm.where(MisCanjes.class).findAll();
        List<MisCanjes> misCanjes = new ArrayList<>();
        for (int x = 0; x < realm.copyToRealm(result).size(); x++) {
            misCanjes.add(result.get(x));
        }
        return misCanjes;

    }

    public MisCanjes getMiCanje(String folio) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MisCanjes> result = realm.where(MisCanjes.class).equalTo("folio", folio).findAll();
        MisCanjes misCanjes = new MisCanjes();
        for (int x = 0; x < result.size(); x++) {
            misCanjes = realm.copyToRealm(result).get(x);
        }

        return misCanjes;
    }


}
