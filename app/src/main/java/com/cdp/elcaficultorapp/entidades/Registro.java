package com.cdp.elcaficultorapp.entidades;

public class Registro {

    private int id;
    private String fechaInicio;
    private String fechaFinal;
    private int pesoRecoleccion;
    private String estadoCafe;
    private String fechaVenta;
    private int pesoVenta;
    private int precioKG;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getPesoRecoleccion() {
        return pesoRecoleccion;
    }

    public void setPesoRecoleccion(int pesoRecoleccion) {
        this.pesoRecoleccion = pesoRecoleccion;
    }

    public String getEstadoCafe() {
        return estadoCafe;
    }

    public void setEstadoCafe(String estadoCafe) {
        this.estadoCafe = estadoCafe;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getPesoVenta() {
        return pesoVenta;
    }

    public void setPesoVenta(int pesoVenta) {
        this.pesoVenta = pesoVenta;
    }

    public int getPrecioKG() {
        return precioKG;
    }

    public void setPrecioKG(int precioKG) {
        this.precioKG = precioKG;
    }
}
