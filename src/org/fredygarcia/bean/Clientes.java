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
public class Clientes {
    private int IDClientes;
    private String nombreClientes;
    private String apellidosClientes;
    private String NITClientes;
    private String telefonoClientes;
    private String direccionClientes;
    private String correoClientes;

    public Clientes() {
    }

    public Clientes(int IDClientes, String nombreClientes, String apellidosClientes, String NITClientes, String telefonoClientes, String direccionClientes, String correoClientes) {
        this.IDClientes = IDClientes;
        this.nombreClientes = nombreClientes;
        this.apellidosClientes = apellidosClientes;
        this.NITClientes = NITClientes;
        this.telefonoClientes = telefonoClientes;
        this.direccionClientes = direccionClientes;
        this.correoClientes = correoClientes;
    }

    public int getIDClientes() {
        return IDClientes;
    }

    public void setIDClientes(int IDClientes) {
        this.IDClientes = IDClientes;
    }

    public String getNombreClientes() {
        return nombreClientes;
    }

    public void setNombreClientes(String nombreClientes) {
        this.nombreClientes = nombreClientes;
    }

    public String getApellidosClientes() {
        return apellidosClientes;
    }

    public void setApellidosClientes(String apellidosClientes) {
        this.apellidosClientes = apellidosClientes;
    }

    public String getNITClientes() {
        return NITClientes;
    }

    public void setNITClientes(String NITClientes) {
        this.NITClientes = NITClientes;
    }

    public String getTelefonoClientes() {
        return telefonoClientes;
    }

    public void setTelefonoClientes(String telefonoClientes) {
        this.telefonoClientes = telefonoClientes;
    }

    public String getDireccionClientes() {
        return direccionClientes;
    }

    public void setDireccionClientes(String direccionClientes) {
        this.direccionClientes = direccionClientes;
    }

    public String getCorreoClientes() {
        return correoClientes;
    }

    public void setCorreoClientes(String correoClientes) {
        this.correoClientes = correoClientes;
    }
    
    @Override
    public String toString() {
        return getIDClientes() + "    |   " + getNombreClientes() + "    |   " + getApellidosClientes() + "    |   " + getNITClientes() + "    |   " + getTelefonoClientes() + "    |   " + getDireccionClientes() + "    |   " + getCorreoClientes();
    }
}
