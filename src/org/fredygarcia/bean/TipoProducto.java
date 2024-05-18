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
public class TipoProducto {
    private int IDTipoProducto;
    private String descripcion;

    public TipoProducto() {
    }

    public TipoProducto(int IDTipoProducto, String descripcion) {
        this.IDTipoProducto = IDTipoProducto;
        this.descripcion = descripcion;
    }

    public int getIDTipoProducto() {
        return IDTipoProducto;
    }

    public void setIDTipoProducto(int IDTipoProducto) {
        this.IDTipoProducto = IDTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return getIDTipoProducto() + "    |   " + getDescripcion();
    }
}
