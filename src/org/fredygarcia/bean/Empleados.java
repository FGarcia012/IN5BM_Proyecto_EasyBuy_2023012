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
public class Empleados {

    private int IDEmpleados;
    private String nombresEmpleado;
    private String apellidosEmpleado;
    private double sueldo;
    private String direccion;
    private String turno;
    private int IDCargoEmpleado;

    public Empleados() {
    }

    public Empleados(int IDEmpleados, String nombresEmpleado, String apellidosEmpleado, double sueldo, String direccion, String turno, int IDCargoEmpleado) {
        this.IDEmpleados = IDEmpleados;
        this.nombresEmpleado = nombresEmpleado;
        this.apellidosEmpleado = apellidosEmpleado;
        this.sueldo = sueldo;
        this.direccion = direccion;
        this.turno = turno;
        this.IDCargoEmpleado = IDCargoEmpleado;
    }

    public int getIDEmpleados() {
        return IDEmpleados;
    }

    public void setIDEmpleados(int IDEmpleados) {
        this.IDEmpleados = IDEmpleados;
    }

    public String getNombresEmpleado() {
        return nombresEmpleado;
    }

    public void setNombresEmpleado(String nombresEmpleado) {
        this.nombresEmpleado = nombresEmpleado;
    }

    public String getApellidosEmpleado() {
        return apellidosEmpleado;
    }

    public void setApellidosEmpleado(String apellidosEmpleado) {
        this.apellidosEmpleado = apellidosEmpleado;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getIDCargoEmpleado() {
        return IDCargoEmpleado;
    }

    public void setIDCargoEmpleado(int IDCargoEmpleado) {
        this.IDCargoEmpleado = IDCargoEmpleado;
    }
    
    @Override
    public String toString() {
        return getIDEmpleados() + "    |   " + getNombresEmpleado() + "    |   " + getApellidosEmpleado() + "    |   " + getSueldo() + "    |   " + getDireccion() + "    |   " + getTurno() + "    |   " + getIDCargoEmpleado();
    }
}
