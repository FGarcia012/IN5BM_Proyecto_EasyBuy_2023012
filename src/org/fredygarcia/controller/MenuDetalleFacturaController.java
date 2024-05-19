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
import org.fredygarcia.bean.DetalleFactura;
import org.fredygarcia.bean.Factura;
import org.fredygarcia.bean.Productos;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuDetalleFacturaController implements Initializable {

    private Main escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<DetalleFactura> listaDetalleFactura;
    private ObservableList<Factura> listaFactura;
    private ObservableList<Productos> listaProductos;

    @FXML
    private TableView tblDetalleFactura;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuH;

    @FXML
    private TextField txtIDDetalleFactura;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtCantidad;

    @FXML
    private ComboBox cmbNumFactura;

    @FXML
    private ComboBox cmbIDProductos;

    @FXML
    private TableColumn colIDDetalleFactura;

    @FXML
    private TableColumn colPrecioUnitario;

    @FXML
    private TableColumn colCantidad;

    @FXML
    private TableColumn colNumFactura;

    @FXML
    private TableColumn colIDProductos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbNumFactura.setItems(getFactura());
        cmbIDProductos.setItems(getProductos());
    }

    public void cargarDatos() {
        tblDetalleFactura.setItems(getDetalleFactura());
        colIDDetalleFactura.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("IDDetalleFactura"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Double>("precioUnitario"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("cantidad"));
        colNumFactura.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("numFactura"));
        colIDProductos.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("IDProductos"));
    }

    public void seleccionarElementos() {
        txtIDDetalleFactura.setText(String.valueOf(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDDetalleFactura()));
        txtPrecioUnitario.setText(String.valueOf(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getPrecioUnitario()));
        txtCantidad.setText(String.valueOf(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getCantidad()));
        cmbNumFactura.getSelectionModel().select(buscarFactura(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getNumFactura()));
        cmbIDProductos.getSelectionModel().select(buscarProductos(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDProductos()));
    }

    public Factura buscarFactura(int numFactura) {
        Factura resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarFactura(?)}");
            procedimiento.setInt(1, numFactura);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Factura(registro.getInt("numFactura"),
                        registro.getString("estado"),
                        registro.getDouble("totalFactura"),
                        registro.getString("fechaFactura"),
                        registro.getInt("IDClientes"),
                        registro.getInt("IDEmpleados")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Productos buscarProductos(String IDProductos) {
        Productos resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarProductos(?)}");
            procedimiento.setString(1, IDProductos);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Productos(registro.getString("IDProductos"),
                        registro.getString("descProducto"),
                        registro.getDouble("precioUnitario"),
                        registro.getDouble("precioDocena"),
                        registro.getDouble("precioMayor"),
                        registro.getString("imagenProducto"),
                        registro.getInt("existencia"),
                        registro.getInt("IDTipoProducto"),
                        registro.getInt("IDProveedor")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ObservableList<DetalleFactura> getDetalleFactura() {
        ArrayList<DetalleFactura> lista = new ArrayList<DetalleFactura>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarDetalleFactura()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new DetalleFactura(resultado.getInt("IDDetalleFactura"),
                        resultado.getDouble("precioUnitario"),
                        resultado.getInt("cantidad"),
                        resultado.getInt("numFactura"),
                        resultado.getString("IDProductos")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetalleFactura = FXCollections.observableList(lista);
    }

    public ObservableList<Factura> getFactura() {
        ArrayList<Factura> lista = new ArrayList<>();
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

    public ObservableList<Productos> getProductos() {
        ArrayList<Productos> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarProductos()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Productos(resultado.getString("IDProductos"),
                        resultado.getString("descProducto"),
                        resultado.getDouble("precioUnitario"),
                        resultado.getDouble("precioDocena"),
                        resultado.getDouble("precioMayor"),
                        resultado.getString("imagenProducto"),
                        resultado.getInt("existencia"),
                        resultado.getInt("IDTipoProducto"),
                        resultado.getInt("IDProveedor")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProductos = FXCollections.observableList(lista);
    }

    public void desactivarControles() {
        txtIDDetalleFactura.setEditable(false);
        txtPrecioUnitario.setEditable(false);
        txtCantidad.setEditable(false);
        cmbNumFactura.setDisable(true);
        cmbIDProductos.setDisable(true);
    }

    public void activarControles() {
        txtIDDetalleFactura.setEditable(true);
        txtPrecioUnitario.setEditable(true);
        txtCantidad.setEditable(true);
        cmbNumFactura.setDisable(false);
        cmbIDProductos.setDisable(false);
    }

    public void limpiarControles() {
        txtIDDetalleFactura.clear();
        txtPrecioUnitario.clear();
        txtCantidad.clear();
        tblDetalleFactura.getSelectionModel().getSelectedItem();
        cmbNumFactura.getSelectionModel().getSelectedItem();
        cmbIDProductos.getSelectionModel().getSelectedItem();

    }
    
    @FXML
    private void Agregar() {
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
        DetalleFactura registro = new DetalleFactura();
        registro.setIDDetalleFactura(Integer.parseInt(txtIDDetalleFactura.getText()));
        registro.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
        registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        registro.setNumFactura((((Factura) cmbNumFactura.getSelectionModel().getSelectedItem()).getNumFactura()));
        registro.setIDProductos(((Productos) cmbIDProductos.getSelectionModel().getSelectedItem()).getIDProductos());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarDetalleFactura(?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDDetalleFactura());
            procedimiento.setDouble(2, registro.getPrecioUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setInt(4, registro.getNumFactura());
            procedimiento.setString(5, registro.getIDProductos());
            procedimiento.execute();
            listaDetalleFactura.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarDetalleFactura(?,?,?,?,?)}");
            DetalleFactura registro = (DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem();
            registro.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
            registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
            registro.setNumFactura((((Factura) cmbNumFactura.getSelectionModel().getSelectedItem()).getNumFactura()));
            registro.setIDProductos(((Productos) cmbIDProductos.getSelectionModel().getSelectedItem()).getIDProductos());
            procedimiento.setInt(1, registro.getIDDetalleFactura());
            procedimiento.setDouble(2, registro.getPrecioUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setInt(4, registro.getNumFactura());
            procedimiento.setString(5, registro.getIDProductos());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblDetalleFactura.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtIDDetalleFactura.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar un Detalle de Factura para editar");
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
                if (tblDetalleFactura.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Detalle de Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarDetalleFactura(?)}");
                            procedimiento.setInt(1, ((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDDetalleFactura());
                            procedimiento.execute();
                            listaDetalleFactura.remove(tblDetalleFactura.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un Detalle factura para eliminar");
                }
        }
    }

    @FXML
    private void reporte() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnReporte.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
            case NULL:
                break;
        }
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuH) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
}
