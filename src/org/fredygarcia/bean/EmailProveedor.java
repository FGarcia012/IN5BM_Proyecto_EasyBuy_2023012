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
public class EmailProveedor {
    private int IDEmailProveedor;
    private String emailProveedor;
    private String descripcion;
    private int IDProveedor;

    public EmailProveedor() {
    }

    public EmailProveedor(int IDEmailProveedor, String emailProveedor, String descripcion, int IDProveedor) {
        this.IDEmailProveedor = IDEmailProveedor;
        this.emailProveedor = emailProveedor;
        this.descripcion = descripcion;
        this.IDProveedor = IDProveedor;
    }

    public int getIDEmailProveedor() {
        return IDEmailProveedor;
    }

    public void setIDEmailProveedor(int IDEmailProveedor) {
        this.IDEmailProveedor = IDEmailProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIDProveedor() {
        return IDProveedor;
    }

    public void setIDProveedor(int IDProveedor) {
        this.IDProveedor = IDProveedor;
    }
}