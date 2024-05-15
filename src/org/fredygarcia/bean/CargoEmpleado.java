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
public class CargoEmpleado {
    private int IDCargoEmpleado;
    private String nombreCargo;
    private String descripcionCargo;

    public CargoEmpleado() {
    }

    public CargoEmpleado(int IDCargoEmpleado, String nombreCargo, String descripcionCargo) {
        this.IDCargoEmpleado = IDCargoEmpleado;
        this.nombreCargo = nombreCargo;
        this.descripcionCargo = descripcionCargo;
    }

    public int getIDCargoEmpleado() {
        return IDCargoEmpleado;
    }

    public void setIDCargoEmpleado(int IDCargoEmpleado) {
        this.IDCargoEmpleado = IDCargoEmpleado;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }
}
