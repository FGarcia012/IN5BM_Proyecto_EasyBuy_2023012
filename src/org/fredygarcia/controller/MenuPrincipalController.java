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
    private MenuItem btnCargoEmpleados;
    
    @FXML
    private MenuItem btnCompras;
    
    @FXML
    private MenuItem btnTipoProductos;
    
    @FXML
    private MenuItem btnProductos;
    
    @FXML
    private MenuItem btnEmpleados;
    
    @FXML
    private MenuItem btnFactura;
    
    @FXML
    private MenuItem btnDetalleFactura;
    
    @FXML
    private MenuItem btnDetalleCompra;
    
    @FXML
    private MenuItem btnTelefonoProveedor;
    
    @FXML
    private MenuItem btnEmailProveedor;
     
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

    public MenuItem getBtnCargoEmpleados() {
        return btnCargoEmpleados;
    }

    public void setBtnCargoEmpleados(MenuItem btnCargoEmpleados) {
        this.btnCargoEmpleados = btnCargoEmpleados;
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

    public MenuItem getBtnEmpleados() {
        return btnEmpleados;
    }

    public void setBtnEmpleados(MenuItem btnEmpleados) {
        this.btnEmpleados = btnEmpleados;
    }

    public MenuItem getBtnFactura() {
        return btnFactura;
    }

    public void setBtnFactura(MenuItem btnFactura) {
        this.btnFactura = btnFactura;
    }

    public MenuItem getBtnDetalleFactura() {
        return btnDetalleFactura;
    }

    public void setBtnDetalleFactura(MenuItem btnDetalleFactura) {
        this.btnDetalleFactura = btnDetalleFactura;
    }

    public MenuItem getBtnDetalleCompra() {
        return btnDetalleCompra;
    }

    public void setBtnDetalleCompra(MenuItem btnDetalleCompra) {
        this.btnDetalleCompra = btnDetalleCompra;
    }

    public MenuItem getBtnTelefonoProveedor() {
        return btnTelefonoProveedor;
    }

    public void setBtnTelefonoProveedor(MenuItem btnTelefonoProveedor) {
        this.btnTelefonoProveedor = btnTelefonoProveedor;
    }

    public MenuItem getBtnEmailProveedor() {
        return btnEmailProveedor;
    }

    public void setBtnEmailProveedor(MenuItem btnEmailProveedor) {
        this.btnEmailProveedor = btnEmailProveedor;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnClientes){
            escenarioPrincipal.menuClienteView();
        }if(event.getSource() == btnProveedores){
            escenarioPrincipal.menuProveedoresView();
        }if(event.getSource() == btnCargoEmpleados){
            escenarioPrincipal.menuCargoEmpleadosView();
        }if(event.getSource() == btnCompras){
            escenarioPrincipal.menuComprasView();
        }if(event.getSource() == btnTipoProductos){
            escenarioPrincipal.menuTipoProductosView();
        }if(event.getSource() == btnProductos){
            escenarioPrincipal.menuProductosView();
        }if(event.getSource() == btnEmpleados){
            escenarioPrincipal.menuEmpleadosView();
        }if(event.getSource() == btnFactura){
            escenarioPrincipal.menuFacturaView();
        }if(event.getSource() == btnDetalleFactura){
            escenarioPrincipal.menuDetalleFacturaView();
        }if(event.getSource() == btnDetalleCompra){
            escenarioPrincipal.menuDetalleCompraView();
        }if(event.getSource() == btnTelefonoProveedor){
            escenarioPrincipal.menuTelefonoProveedorView();
        }if(event.getSource() == btnEmailProveedor){
            escenarioPrincipal.menuEmailProveedorView();
        }if(event.getSource() == btnProgramador)
            escenarioPrincipal.prograView();
    }
}