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
import org.fredygarcia.bean.Productos;
import org.fredygarcia.bean.Proveedores;
import org.fredygarcia.bean.TipoProducto;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuProductosController implements Initializable {

    private Main escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<Productos> listaProductos;
    private ObservableList<TipoProducto> listaTipoProducto;
    private ObservableList<Proveedores> listaProveedores;

    @FXML
    private TableView tblProductos;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuE;

    @FXML
    private TextField txtIDProductos;

    @FXML
    private TextField txtDescProducto;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtPrecioDocena;

    @FXML
    private TextField txtPrecioMayor;

    @FXML
    private TextField txtImagenProducto;

    @FXML
    private TextField txtExistencia;

    @FXML
    private ComboBox cmbIDTipoProducto;

    @FXML
    private ComboBox cmbIDProveedor;

    @FXML
    private TableColumn colIDProductos;

    @FXML
    private TableColumn colDescProducto;

    @FXML
    private TableColumn colPrecioUnitario;

    @FXML
    private TableColumn colPrecioDocena;

    @FXML
    private TableColumn colPrecioMayor;

    @FXML
    private TableColumn colImagenProducto;

    @FXML
    private TableColumn colExistencia;

    @FXML
    private TableColumn colIDTipoProducto;

    @FXML
    private TableColumn colIDProveedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbIDTipoProducto.setItems(getTipoProductos());
        cmbIDProveedor.setItems(getProveedores());
    }

    public void cargarDatos() {
        tblProductos.setItems(getProductos());
        colIDProductos.setCellValueFactory(new PropertyValueFactory<Productos, String>("IDProductos"));
        colDescProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("descProducto"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioUnitario"));
        colPrecioDocena.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioDocena"));
        colPrecioMayor.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioMayor"));
        colImagenProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("imagenProducto"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("existencia"));
        colIDTipoProducto.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("IDTipoProducto"));
        colIDProveedor.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("IDProveedor"));
    }

    public void seleccionarElementos() {
        txtIDProductos.setText((((Productos) tblProductos.getSelectionModel().getSelectedItem()).getIDProductos()));
        txtDescProducto.setText((((Productos) tblProductos.getSelectionModel().getSelectedItem()).getDescProducto()));
        txtPrecioUnitario.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioUnitario()));
        txtPrecioDocena.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioDocena()));
        txtPrecioMayor.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioMayor()));
        txtImagenProducto.setText((((Productos) tblProductos.getSelectionModel().getSelectedItem()).getImagenProducto()));
        txtExistencia.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getExistencia()));
        cmbIDTipoProducto.getSelectionModel().select(buscarTipoProducto(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getIDTipoProducto()));
        cmbIDProveedor.getSelectionModel().select(buscarProveedores(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getIDProveedor()));
    }

    public TipoProducto buscarTipoProducto(int IDTipoProducto) {
        TipoProducto resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarTipoProducto(?)}");
            procedimiento.setInt(1, IDTipoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new TipoProducto(registro.getInt("IDTipoProducto"),
                        registro.getString("descripcion")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Proveedores buscarProveedores(int IDProveedor) {
        Proveedores resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarProveedor(?)}");
            procedimiento.setInt(1, IDProveedor);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Proveedores(registro.getInt("IDProveedor"),
                        registro.getString("nombresProveedor"),
                        registro.getString("apellidosProveedor"),
                        registro.getString("NITProveedor"),
                        registro.getString("telefonoProveedor"),
                        registro.getString("direccionProveedor"),
                        registro.getString("correoProveedor"),
                        registro.getString("razonSocial"),
                        registro.getString("contactoPrincipal"),
                        registro.getString("paginaWeb")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ObservableList<Productos> getProductos() {
        ArrayList<Productos> lista = new ArrayList<Productos>();
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
    
    public ObservableList<TipoProducto> getTipoProductos() {
        ArrayList<TipoProducto> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarTipoProducto()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new TipoProducto(resultado.getInt("IDTipoProducto"),
                        resultado.getString("descripcion")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTipoProducto = FXCollections.observableList(lista);
    }

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarProveedores()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Proveedores(resultado.getInt("IDProveedor"),
                        resultado.getString("nombresProveedor"),
                        resultado.getString("apellidosProveedor"),
                        resultado.getString("NITProveedor"),
                        resultado.getString("telefonoProveedor"),
                        resultado.getString("direccionProveedor"),
                        resultado.getString("correoProveedor"),
                        resultado.getString("razonSocial"),
                        resultado.getString("contactoPrincipal"),
                        resultado.getString("paginaWeb")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProveedores = FXCollections.observableList(lista);
    }

    public void desactivarControles() {
        txtIDProductos.setEditable(false);
        txtDescProducto.setEditable(false);
        txtPrecioUnitario.setEditable(false);
        txtPrecioDocena.setEditable(false);
        txtPrecioMayor.setEditable(false);
        txtImagenProducto.setEditable(false);
        txtExistencia.setEditable(false);
        cmbIDTipoProducto.setDisable(true);
        cmbIDProveedor.setDisable(true);
    }

    public void activarControles() {
        txtIDProductos.setEditable(true);
        txtDescProducto.setEditable(true);
        txtPrecioUnitario.setEditable(true);
        txtPrecioDocena.setEditable(true);
        txtPrecioMayor.setEditable(true);
        txtImagenProducto.setEditable(true);
        txtExistencia.setEditable(true);
        cmbIDTipoProducto.setDisable(false);
        cmbIDProveedor.setDisable(false);
    }

    public void limpiarControles() {
        txtIDProductos.clear();
        txtDescProducto.clear();
        txtPrecioUnitario.clear();
        txtPrecioDocena.clear();
        txtPrecioMayor.clear();
        txtImagenProducto.clear();
        txtExistencia.clear();
        tblProductos.getSelectionModel().getSelectedItem();
        cmbIDTipoProducto.getSelectionModel().getSelectedItem();
        cmbIDProveedor.getSelectionModel().getSelectedItem();

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
        Productos registro = new Productos();
        registro.setIDProductos(txtIDProductos.getText());
        registro.setDescProducto(txtDescProducto.getText());
        registro.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
        registro.setPrecioDocena(Double.parseDouble(txtPrecioDocena.getText()));
        registro.setPrecioMayor(Double.parseDouble(txtPrecioMayor.getText()));
        registro.setImagenProducto(txtImagenProducto.getText());
        registro.setExistencia(Integer.parseInt(txtExistencia.getText()));
        registro.setIDTipoProducto((((TipoProducto) cmbIDTipoProducto.getSelectionModel().getSelectedItem()).getIDTipoProducto()));
        registro.setIDProveedor(((Proveedores) cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedor());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarProductos(?,?,?,?,?,?,?,?,?)}");
            procedimiento.setString(1, registro.getIDProductos());
            procedimiento.setString(2, registro.getDescProducto());
            procedimiento.setDouble(3, registro.getPrecioUnitario());
            procedimiento.setDouble(4, registro.getPrecioDocena());
            procedimiento.setDouble(5, registro.getPrecioMayor());
            procedimiento.setString(6, registro.getImagenProducto());
            procedimiento.setInt(7, registro.getExistencia());
            procedimiento.setInt(8, registro.getIDTipoProducto());
            procedimiento.setInt(9, registro.getIDProveedor());
            procedimiento.execute();
            listaProductos.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarProductos(?,?,?,?,?,?,?,?,?)}");
            Productos registro = (Productos) tblProductos.getSelectionModel().getSelectedItem();
            registro.setDescProducto(txtDescProducto.getText());
            registro.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
            registro.setPrecioDocena(Double.parseDouble(txtPrecioDocena.getText()));
            registro.setPrecioMayor(Double.parseDouble(txtPrecioMayor.getText()));
            registro.setImagenProducto(txtImagenProducto.getText());
            registro.setExistencia(Integer.parseInt(txtExistencia.getText()));
            registro.setIDTipoProducto((((TipoProducto) cmbIDTipoProducto.getSelectionModel().getSelectedItem()).getIDTipoProducto()));
            registro.setIDProveedor(((Proveedores) cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedor());
            procedimiento.setString(1, registro.getIDProductos());
            procedimiento.setString(2, registro.getDescProducto());
            procedimiento.setDouble(3, registro.getPrecioUnitario());
            procedimiento.setDouble(4, registro.getPrecioDocena());
            procedimiento.setDouble(5, registro.getPrecioMayor());
            procedimiento.setString(6, registro.getImagenProducto());
            procedimiento.setInt(7, registro.getExistencia());
            procedimiento.setInt(8, registro.getIDTipoProducto());
            procedimiento.setInt(9, registro.getIDProveedor());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblProductos.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtIDProductos.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar un producto para editar");
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
                if (tblProductos.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarProductos(?)}");
                            procedimiento.setString(1, ((Productos) tblProductos.getSelectionModel().getSelectedItem()).getIDProductos());
                            procedimiento.execute();
                            listaProductos.remove(tblProductos.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un producto para eliminar");
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
        if (event.getSource() == btnMenuE) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
}
