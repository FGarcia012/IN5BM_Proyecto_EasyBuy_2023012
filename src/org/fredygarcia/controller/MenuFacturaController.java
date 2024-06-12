/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fredygarcia.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.fredygarcia.bean.Clientes;
import org.fredygarcia.bean.Empleados;
import org.fredygarcia.bean.Factura;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.report.GenerarReportes;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuFacturaController implements Initializable{
    
    private Main escenarioPrincipal;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<Factura> listaFactura;
    private ObservableList<Clientes> listaClientes;
    private ObservableList<Empleados> listaEmpleados;
    
    @FXML
    private TableView tblFactura;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuG;
    
    @FXML
    private Button btnClientes;

    @FXML
    private Button btnEmpleados;
    
    @FXML
    private TextField txtNumFactura;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtTotalFactura;

    @FXML
    private TextField txtFechaFactura;

    @FXML
    private ComboBox cmbIDCliente;

    @FXML
    private ComboBox cmbIDEmpleados;
    
    @FXML
    private TableColumn colNumFactura;

    @FXML
    private TableColumn colEstado;

    @FXML
        private TableColumn colTotalFactura;

    @FXML
    private TableColumn colFechaFactura;

    @FXML
    private TableColumn colIDCliente;

    @FXML
    private TableColumn colIDEmpleados;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbIDCliente.setItems(getClientes());
        cmbIDEmpleados.setItems(getEmpleados());
    }
    
    public void cargarDatos(){
        tblFactura.setItems(getFactura());
        colNumFactura.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("numFactura"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Factura, String>("estado"));
        colTotalFactura.setCellValueFactory(new PropertyValueFactory<Factura, Double>("totalFactura"));
        colFechaFactura.setCellValueFactory(new PropertyValueFactory<Factura, String>("fechaFactura"));
        colIDCliente.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("IDClientes"));
        colIDEmpleados.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("IDEmpleados"));
    }
    
    public void seleccionarElementos() {
        txtNumFactura.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getNumFactura()));
        txtEstado.setText((((Factura) tblFactura.getSelectionModel().getSelectedItem()).getEstado()));
        txtTotalFactura.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getTotalFactura()));
        txtFechaFactura.setText((((Factura) tblFactura.getSelectionModel().getSelectedItem()).getFechaFactura()));
        cmbIDCliente.getSelectionModel().select(buscarClientes(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDClientes()));
        cmbIDEmpleados.getSelectionModel().select(buscarEmpleados(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDEmpleados()));
    }
    
    public Clientes buscarClientes(int IDClientes) {
        Clientes resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarClientes(?)}");
            procedimiento.setInt(1, IDClientes);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Clientes(registro.getInt("IDClientes"),
                        registro.getString("nombreClientes"),
                        registro.getString("apellidosClientes"),
                        registro.getString("NITClientes"),
                        registro.getString("telefonoClientes"),
                        registro.getString("direccionClientes"),
                        registro.getString("correoClientes")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public Empleados buscarEmpleados(int IDEmpleados) {
        Empleados resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarEmpleados(?)}");
            procedimiento.setInt(1, IDEmpleados);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Empleados(registro.getInt("IDEmpleados"),
                        registro.getString("nombresEmpleado"),
                        registro.getString("apellidosEmpleado"),
                        registro.getDouble("sueldo"),
                        registro.getString("direccion"),
                        registro.getString("turno"),
                        registro.getInt("IDCargoEmpleado")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList<Factura> getFactura() {
        ArrayList<Factura> lista = new ArrayList<Factura>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarFactura()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Factura(resultado.getInt("numFactura"),
                        resultado.getString("estado"),
                        resultado.getDouble("totalFactura"),
                        resultado.getString("fechaFactura"),
                        resultado.getInt("IDClientes"),
                        resultado.getInt("IDEmpleados")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFactura = FXCollections.observableList(lista);
    }
    
    public ObservableList<Clientes> getClientes() {
        ArrayList<Clientes> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarClientes()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Clientes(resultado.getInt("IDClientes"),
                        resultado.getString("nombreClientes"),
                        resultado.getString("apellidosClientes"),
                        resultado.getString("NITClientes"),
                        resultado.getString("telefonoClientes"),
                        resultado.getString("direccionClientes"),
                        resultado.getString("correoClientes")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaClientes = FXCollections.observableList(lista);
    }

    public ObservableList<Empleados> getEmpleados() {
        ArrayList<Empleados> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarEmpleados()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Empleados(resultado.getInt("IDEmpleados"),
                        resultado.getString("nombresEmpleado"),
                        resultado.getString("apellidosEmpleado"),
                        resultado.getDouble("sueldo"),
                        resultado.getString("direccion"),
                        resultado.getString("turno"),
                        resultado.getInt("IDCargoEmpleado")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpleados = FXCollections.observableList(lista);
    }
    
    public void desactivarControles() {
        txtNumFactura.setEditable(false);
        txtEstado.setEditable(false);
        txtTotalFactura.setEditable(false);
        txtFechaFactura.setEditable(false);
        cmbIDCliente.setDisable(true);
        cmbIDEmpleados.setDisable(true);
    }

    public void activarControles() {
        txtNumFactura.setEditable(true);
        txtEstado.setEditable(true);
        txtTotalFactura.setEditable(true);
        txtFechaFactura.setEditable(true);
        cmbIDCliente.setDisable(false);
        cmbIDEmpleados.setDisable(false);
    }

    public void limpiarControles() {
        txtNumFactura.clear();
        txtEstado.clear();
        txtTotalFactura.clear();
        txtFechaFactura.clear();
        tblFactura.getSelectionModel().getSelectedItem();
        cmbIDCliente.getSelectionModel().getSelectedItem();
        cmbIDEmpleados.getSelectionModel().getSelectedItem();

    }
    
    
    public void agregar() {
        switch (tipoDeOperaciones) {
            case NULL:
                activarControles();
                btnAgregar.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                tipoDeOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardar();
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                cargarDatos();
                break;
        }
    }
    
    public void guardar() {
        Factura registro = new Factura();
        registro.setNumFactura(Integer.parseInt(txtNumFactura.getText()));
        registro.setEstado(txtEstado.getText());
        registro.setTotalFactura(Double.parseDouble(txtTotalFactura.getText()));
        registro.setFechaFactura(txtFechaFactura.getText());
        registro.setIDClientes((((Clientes) cmbIDCliente.getSelectionModel().getSelectedItem()).getIDClientes()));
        registro.setIDEmpleados(((Empleados) cmbIDEmpleados.getSelectionModel().getSelectedItem()).getIDEmpleados());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarFactura(?,?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getNumFactura());
            procedimiento.setString(2, registro.getEstado());
            procedimiento.setDouble(3, registro.getTotalFactura());
            procedimiento.setString(4, registro.getFechaFactura());
            procedimiento.setInt(5, registro.getIDClientes());
            procedimiento.setInt(6, registro.getIDEmpleados());
            procedimiento.execute();
            listaFactura.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarFactura(?,?,?,?,?,?)}");
            Factura registro = (Factura) tblFactura.getSelectionModel().getSelectedItem();
            registro.setEstado(txtEstado.getText());
            registro.setTotalFactura(Double.parseDouble(txtTotalFactura.getText()));
            registro.setFechaFactura(txtFechaFactura.getText());
            registro.setIDClientes((((Clientes) cmbIDCliente.getSelectionModel().getSelectedItem()).getIDClientes()));
            registro.setIDEmpleados(((Empleados) cmbIDEmpleados.getSelectionModel().getSelectedItem()).getIDEmpleados());
            procedimiento.setInt(1, registro.getNumFactura());
            procedimiento.setString(2, registro.getEstado());
            procedimiento.setDouble(3, registro.getTotalFactura());
            procedimiento.setString(4, registro.getFechaFactura());
            procedimiento.setInt(5, registro.getIDClientes());
            procedimiento.setInt(6, registro.getIDEmpleados());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblFactura.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtNumFactura.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar una factura para editar");
                }
                break;
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOperaciones = operaciones.NULL;
                cargarDatos();
                break;
        }
    }

    @FXML
    private void eliminar() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                tipoDeOperaciones = operaciones.NULL;
                break;
            default:
                if (tblFactura.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarFactura(?)}");
                            procedimiento.setInt(1, ((Factura) tblFactura.getSelectionModel().getSelectedItem()).getNumFactura());
                            procedimiento.execute();
                            listaFactura.remove(tblFactura.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar una factura para eliminar");
                }
        }
    }

    @FXML
    private void reporte() {
        switch (tipoDeOperaciones) {
            case NULL:
                imprimirReporte();
                break;
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnReporte.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                break;
        }
    }
    
    public void imprimirReporte(){
            Map parametros = new HashMap();
            int IDFac = ((Factura)tblFactura.getSelectionModel().getSelectedItem()).getNumFactura();
            parametros.put("IDFac", IDFac);
            GenerarReportes.mostrarReportes("reporteFactura.jasper", "Reporte de Factura", parametros);
        }

    public Button getBtnClientes() {
        return btnClientes;
    }

    public void setBtnClientes(Button btnClientes) {
        this.btnClientes = btnClientes;
    }

    public Button getBtnEmpleados() {
        return btnEmpleados;
    }

    public void setBtnEmpleados(Button btnEmpleados) {
        this.btnEmpleados = btnEmpleados;
    }

    
    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }   
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuG) {
            escenarioPrincipal.menuPrincipalView();
        }if (event.getSource() == btnClientes) {
            escenarioPrincipal.menuClienteView();
        }if (event.getSource() == btnEmpleados) {
            escenarioPrincipal.menuEmpleadosView();
        }
    }
}