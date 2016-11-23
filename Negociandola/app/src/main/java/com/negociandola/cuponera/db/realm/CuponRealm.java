package com.negociandola.cuponera.db.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lapprestamo on 28/10/16.
 */

public class CuponRealm extends RealmObject{

    @PrimaryKey
    private int id;
    private String tituloCupon;
    private String imagenCupon;
    private String nombreNegocio;
    private String vencimiento;
    private String folio;
    private String restricciones;
    private String descripcionNegocio;
    private String horarioNegocio;
    private String direccionNegocio;
    private String telefonoNegocio;
    private boolean isCanjeado;
    private boolean isAprobado;

    public boolean isAprobado() {
        return isAprobado;
    }

    public void setAprobado(boolean aprobado) {
        isAprobado = aprobado;
    }

    public boolean isCanjeado() {
        return isCanjeado;
    }

    public void setCanjeado(boolean canjeado) {
        isCanjeado = canjeado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloCupon() {
        return tituloCupon;
    }

    public void setTituloCupon(String tituloCupon) {
        this.tituloCupon = tituloCupon;
    }

    public String getImagenCupon() {
        return imagenCupon;
    }

    public void setImagenCupon(String imagenCupon) {
        this.imagenCupon = imagenCupon;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public String getDescripcionNegocio() {
        return descripcionNegocio;
    }

    public void setDescripcionNegocio(String descripcionNegocio) {
        this.descripcionNegocio = descripcionNegocio;
    }

    public String getHorarioNegocio() {
        return horarioNegocio;
    }

    public void setHorarioNegocio(String horarioNegocio) {
        this.horarioNegocio = horarioNegocio;
    }

    public String getDireccionNegocio() {
        return direccionNegocio;
    }

    public void setDireccionNegocio(String direccionNegocio) {
        this.direccionNegocio = direccionNegocio;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }
}
