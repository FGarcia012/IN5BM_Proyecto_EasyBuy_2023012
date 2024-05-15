/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fredygarcia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuPrincipalController implements Initializable{
    private Main escenarioPrincipal;

    @FXML 
    private MenuItem btnClientes;
    
    @FXML 
    private MenuItem btnProgramador;
    
    @FXML
    private MenuItem btnProveedores;
    
    @FXML
    private MenuItem btnEmpleados;
    
    @FXML
    private MenuItem btnCompras;
    
    @FXML
    private MenuItem btnTipoProductos;
    
    @FXML
    private MenuItem btnProductos;
     
    @Override
    public void initialize(URL location,ResourceBundle Resources){
        
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public MenuItem getBtnClientes() {
        return btnClientes;
    }

    public void setBtnClientes(MenuItem btnClientes) {
        this.btnClientes = btnClientes;
    }

    public MenuItem getBtnProgramador() {
        return btnProgramador;
    }

    public void setBtnProgramador(MenuItem btnProgramador) {
        this.btnProgramador = btnProgramador;
    }

    public MenuItem getBtnProveedores() {
        return btnProveedores;
    }

    public void setBtnProveedores(MenuItem btnProveedores) {
        this.btnProveedores = btnProveedores;
    }

    public MenuItem getBtnEmpleados() {
        return btnEmpleados;
    }

    public void setBtnEmpleados(MenuItem btnEmpleados) {
        this.btnEmpleados = btnEmpleados;
    }

    public MenuItem getBtnCompras() {
        return btnCompras;
    }

    public void setBtnCompras(MenuItem btnCompras) {
        this.btnCompras = btnCompras;
    }

    public MenuItem getBtnTipoProductos() {
        return btnTipoProductos;
    }

    public void setBtnTipoProductos(MenuItem btnTipoProductos) {
        this.btnTipoProductos = btnTipoProductos;
    }

    public MenuItem getBtnProductos() {
        return btnProductos;
    }

    public void setBtnProductos(MenuItem btnProductos) {
        this.btnProductos = btnProductos;
    } 
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnClientes){
            escenarioPrincipal.menuClienteView();
        }if(event.getSource() == btnProveedores){
            escenarioPrincipal.menuProveedoresView();
        }if(event.getSource() == btnEmpleados){
            escenarioPrincipal.menuEmpleadosView();
        }if(event.getSource() == btnCompras){
            escenarioPrincipal.menuComprasView();
        }if(event.getSource() == btnTipoProductos){
            escenarioPrincipal.menuTipoProductosView();
        }if(event.getSource() == btnProductos){
            escenarioPrincipal.menuProductosView();
        }if(event.getSource() == btnProgramador)
            escenarioPrincipal.prograView();
    }
}