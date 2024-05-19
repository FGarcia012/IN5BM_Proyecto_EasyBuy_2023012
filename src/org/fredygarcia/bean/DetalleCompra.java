/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fredygarcia.bean;

/**
 *
 * @author alexa
 */
public class DetalleCompra {
    private int IDDetalleCompra;
    private double costoUnitario;
    private int cantidad;
    private String IDProductos;
    private int numDocumento;

    public DetalleCompra() {
    }

    public DetalleCompra(int IDDetalleCompra, double costoUnitario, int cantidad, String IDProductos, int numDocumento) {
        this.IDDetalleCompra = IDDetalleCompra;
        this.costoUnitario = costoUnitario;
        this.cantidad = cantidad;
        this.IDProductos = IDProductos;
        this.numDocumento = numDocumento;
    }

    public int getIDDetalleCompra() {
        return IDDetalleCompra;
    }

    public void setIDDetalleCompra(int IDDetalleCompra) {
        this.IDDetalleCompra = IDDetalleCompra;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIDProductos() {
        return IDProductos;
    }

    public void setIDProductos(String IDProductos) {
        this.IDProductos = IDProductos;
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        this.numDocumento = numDocumento;
    }
}
