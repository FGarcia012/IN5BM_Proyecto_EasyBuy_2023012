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
public class Productos {
    private String IDProductos;
    private String descProducto;
    private double precioUnitario;
    private double precioDocena;
    private double precioMayor;
    private String imagenProducto;
    private int existencia;
    private int IDTipoProducto;
    private int IDProveedor;

    public Productos() {
    }

    public Productos(String IDProductos, String descProducto, double precioUnitario, double precioDocena, double precioMayor, String imagenProducto, int existencia, int IDTipoProducto, int IDProveedor) {
        this.IDProductos = IDProductos;
        this.descProducto = descProducto;
        this.precioUnitario = precioUnitario;
        this.precioDocena = precioDocena;
        this.precioMayor = precioMayor;
        this.imagenProducto = imagenProducto;
        this.existencia = existencia;
        this.IDTipoProducto = IDTipoProducto;
        this.IDProveedor = IDProveedor;
    }

    public String getIDProductos() {
        return IDProductos;
    }

    public void setIDProductos(String IDProductos) {
        this.IDProductos = IDProductos;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioDocena() {
        return precioDocena;
    }

    public void setPrecioDocena(double precioDocena) {
        this.precioDocena = precioDocena;
    }

    public double getPrecioMayor() {
        return precioMayor;
    }

    public void setPrecioMayor(double precioMayor) {
        this.precioMayor = precioMayor;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getIDTipoProducto() {
        return IDTipoProducto;
    }

    public void setIDTipoProducto(int IDTipoProducto) {
        this.IDTipoProducto = IDTipoProducto;
    }

    public int getIDProveedor() {
        return IDProveedor;
    }

    public void setIDProveedor(int IDProveedor) {
        this.IDProveedor = IDProveedor;
    }
}
