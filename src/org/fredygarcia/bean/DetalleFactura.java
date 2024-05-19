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
public class DetalleFactura {
    private int IDDetalleFactura;
    private double precioUnitario;
    private int cantidad;
    private int numFactura;
    private String IDProductos;

    public DetalleFactura() {
    }

    public DetalleFactura(int IDDetalleFactura, double precioUnitario, int cantidad, int numFactura, String IDProductos) {
        this.IDDetalleFactura = IDDetalleFactura;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.numFactura = numFactura;
        this.IDProductos = IDProductos;
    }

    public int getIDDetalleFactura() {
        return IDDetalleFactura;
    }

    public void setIDDetalleFactura(int IDDetalleFactura) {
        this.IDDetalleFactura = IDDetalleFactura;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getIDProductos() {
        return IDProductos;
    }

    public void setIDProductos(String IDProductos) {
        this.IDProductos = IDProductos;
    }
}
