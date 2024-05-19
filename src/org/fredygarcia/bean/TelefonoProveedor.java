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
public class TelefonoProveedor {
    private int IDTelefonoProveedor;
    private String numeroPrincipal;
    private String numeroSecundario;
    private String observaciones;
    private int IDProveedor;

    public TelefonoProveedor() {
    }

    public TelefonoProveedor(int IDTelefonoProveedor, String numeroPrincipal, String numeroSecundario, String observaciones, int IDProveedor) {
        this.IDTelefonoProveedor = IDTelefonoProveedor;
        this.numeroPrincipal = numeroPrincipal;
        this.numeroSecundario = numeroSecundario;
        this.observaciones = observaciones;
        this.IDProveedor = IDProveedor;
    }

    public int getIDTelefonoProveedor() {
        return IDTelefonoProveedor;
    }

    public void setIDTelefonoProveedor(int IDTelefonoProveedor) {
        this.IDTelefonoProveedor = IDTelefonoProveedor;
    }

    public String getNumeroPrincipal() {
        return numeroPrincipal;
    }

    public void setNumeroPrincipal(String numeroPrincipal) {
        this.numeroPrincipal = numeroPrincipal;
    }

    public String getNumeroSecundario() {
        return numeroSecundario;
    }

    public void setNumeroSecundario(String numeroSecundario) {
        this.numeroSecundario = numeroSecundario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIDProveedor() {
        return IDProveedor;
    }

    public void setIDProveedor(int IDProveedor) {
        this.IDProveedor = IDProveedor;
    }
}