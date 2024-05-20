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
import org.fredygarcia.bean.Compras;
import org.fredygarcia.bean.DetalleCompra;
import org.fredygarcia.bean.Productos;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuDetalleCompraController implements Initializable {
    
    private Main escenarioPrincipal;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<DetalleCompra> listaDetalleCompra;
    private ObservableList<Productos> listaProductos;
    private ObservableList<Compras> listaCompras;
    
    @FXML
    private TableView tblDetalleCompra;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuI;
    
    @FXML
    private Button btnProductos;

    @FXML
    private Button btnCompras;

    @FXML
    private TextField txtIDDetalleCompra;

    @FXML
    private TextField txtCostoUnitario;

    @FXML
    private TextField txtCantidad;

    @FXML
    private ComboBox cmbIDProductos;

    @FXML
    private ComboBox cmbNumDocumento;

    @FXML
    private TableColumn colIDDetalleCompra;

    @FXML
    private TableColumn colCostoUnitario;

    @FXML
    private TableColumn colCantidad;

    @FXML
    private TableColumn colIDProductos;

    @FXML
    private TableColumn colNumDocumento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbIDProductos.setItems(getProductos());
        cmbNumDocumento.setItems(getCompras());
    }
    
    public void cargarDatos() {
        tblDetalleCompra.setItems(getDetalleCompra());
        colIDDetalleCompra.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("IDDetalleCompra"));
        colCostoUnitario.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Double>("costoUnitario"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("cantidad"));
        colIDProductos.setCellValueFactory(new PropertyValueFactory<DetalleCompra, String>("IDProductos"));
        colNumDocumento.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("numDocumento"));
    }
    
    public void seleccionarElementos() {
        txtIDDetalleCompra.setText(String.valueOf(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDDetalleCompra()));
        txtCostoUnitario.setText(String.valueOf(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getCostoUnitario()));
        txtCantidad.setText(String.valueOf(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getCantidad()));
        cmbIDProductos.getSelectionModel().select(buscarProductos(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDProductos()));
        cmbNumDocumento.getSelectionModel().select(buscarCompras(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getNumDocumento()));
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

    public Compras buscarCompras(int numDocumento) {
        Compras resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarCompra(?)}");
            procedimiento.setInt(1, numDocumento);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Compras(registro.getInt("numDocumento"),
                        registro.getString("fechaDocumento"),
                        registro.getString("descripcion"),
                        registro.getDouble("totalDocumento")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList<DetalleCompra> getDetalleCompra() {
        ArrayList<DetalleCompra> lista = new ArrayList<DetalleCompra>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarDetalleCompra()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new DetalleCompra(resultado.getInt("IDDetalleCompra"),
                        resultado.getDouble("costoUnitario"),
                        resultado.getInt("cantidad"),
                        resultado.getString("IDProductos"),
                        resultado.getInt("numDocumento")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetalleCompra = FXCollections.observableList(lista);
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

    public ObservableList<Compras> getCompras() {
        ArrayList<Compras> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarCompras()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Compras(resultado.getInt("numDocumento"),
                        resultado.getString("fechaDocumento"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("totalDocumento")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompras = FXCollections.observableList(lista);
    }
    
    public void desactivarControles() {
        txtIDDetalleCompra.setEditable(false);
        txtCostoUnitario.setEditable(false);
        txtCantidad.setEditable(false);
        cmbIDProductos.setDisable(true);
        cmbNumDocumento.setDisable(true);
    }

    public void activarControles() {
        txtIDDetalleCompra.setEditable(true);
        txtCostoUnitario.setEditable(true);
        txtCantidad.setEditable(true);
        cmbIDProductos.setDisable(false);
        cmbNumDocumento.setDisable(false);
    }

    public void limpiarControles() {
        txtIDDetalleCompra.clear();
        txtCostoUnitario.clear();
        txtCantidad.clear();
        tblDetalleCompra.getSelectionModel().getSelectedItem();
        cmbIDProductos.getSelectionModel().getSelectedItem();
        cmbNumDocumento.getSelectionModel().getSelectedItem();

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
        DetalleCompra registro = new DetalleCompra();
        registro.setIDDetalleCompra(Integer.parseInt(txtIDDetalleCompra.getText()));
        registro.setCostoUnitario(Double.parseDouble(txtCostoUnitario.getText()));
        registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        registro.setIDProductos((((Productos) cmbIDProductos.getSelectionModel().getSelectedItem()).getIDProductos()));
        registro.setNumDocumento(((Compras) cmbNumDocumento.getSelectionModel().getSelectedItem()).getNumDocumento());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarDetalleCompra(?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDDetalleCompra());
            procedimiento.setDouble(2, registro.getCostoUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setString(4, registro.getIDProductos());
            procedimiento.setInt(5, registro.getNumDocumento());
            procedimiento.execute();
            listaDetalleCompra.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarDetalleCompra(?,?,?,?,?)}");
            DetalleCompra registro = (DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem();
            registro.setCostoUnitario(Double.parseDouble(txtCostoUnitario.getText()));
            registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
            registro.setIDProductos((((Productos) cmbIDProductos.getSelectionModel().getSelectedItem()).getIDProductos()));
            registro.setNumDocumento(((Compras) cmbNumDocumento.getSelectionModel().getSelectedItem()).getNumDocumento());
            procedimiento.setInt(1, registro.getIDDetalleCompra());
            procedimiento.setDouble(2, registro.getCostoUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setString(4, registro.getIDProductos());
            procedimiento.setInt(5, registro.getNumDocumento());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblDetalleCompra.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtIDDetalleCompra.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar un Detalle de compra para editar");
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
                if (tblDetalleCompra.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Detalle de compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarDetalleCompra(?)}");
                            procedimiento.setInt(1, ((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDDetalleCompra());
                            procedimiento.execute();
                            listaDetalleCompra.remove(tblDetalleCompra.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un Detalle compra para eliminar");
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

    public Button getBtnProductos() {
        return btnProductos;
    }

    public void setBtnProductos(Button btnProductos) {
        this.btnProductos = btnProductos;
    }

    public Button getBtnCompras() {
        return btnCompras;
    }

    public void setBtnCompras(Button btnCompras) {
        this.btnCompras = btnCompras;
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuI) {
            escenarioPrincipal.menuPrincipalView();
        }if (event.getSource() == btnProductos) {
            escenarioPrincipal.menuProductosView();
        }if (event.getSource() == btnCompras) {
            escenarioPrincipal.menuComprasView();
        }
    }
}