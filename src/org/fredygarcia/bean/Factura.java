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
public class Factura {
    
    private int numFactura;
    private String estado;
    private double totalFactura;
    private String fechaFactura;
    private int IDClientes;
    private int IDEmpleados;

    public Factura() {
    }

    public Factura(int numFactura, String estado, double totalFactura, String fechaFactura, int IDClientes, int IDEmpleados) {
        this.numFactura = numFactura;
        this.estado = estado;
        this.totalFactura = totalFactura;
        this.fechaFactura = fechaFactura;
        this.IDClientes = IDClientes;
        this.IDEmpleados = IDEmpleados;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getIDClientes() {
        return IDClientes;
    }

    public void setIDClientes(int IDClientes) {
        this.IDClientes = IDClientes;
    }

    public int getIDEmpleados() {
        return IDEmpleados;
    }

    public void setIDEmpleados(int IDEmpleados) {
        this.IDEmpleados = IDEmpleados;
    }
    
    @Override
    public String toString() {
        return getNumFactura() + "    |   " + getEstado() + "    |   " + getTotalFactura() + "    |   " + getFechaFactura() + "    |   " + getIDClientes() + "    |   " + getIDEmpleados();
    }
}
