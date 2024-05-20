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
import org.fredygarcia.bean.EmailProveedor;
import org.fredygarcia.bean.Proveedores;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuEmailProveedorController implements Initializable {
    
    private Main escenarioPrincipal;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<EmailProveedor> listaEmailProveedor;
    private ObservableList<Proveedores> listaProveedores;
    
    @FXML
    private TableView tblEmailProveedor;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuK;
    
    @FXML
    private Button btnProveedores;

    @FXML
    private TextField txtIDEmailProveedor;

    @FXML
    private TextField txtEmailProveedor;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private ComboBox cmbIDProveedor;

    @FXML
    private TableColumn colIDEmailProveedor;

    @FXML
    private TableColumn colEmailProveedor;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colIDProveedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbIDProveedor.setItems(getProveedores());
    }
    
    public void cargarDatos() {
        tblEmailProveedor.setItems(getEmailProveedor());
        colIDEmailProveedor.setCellValueFactory(new PropertyValueFactory<EmailProveedor, Integer>("IDEmailProveedor"));
        colEmailProveedor.setCellValueFactory(new PropertyValueFactory<EmailProveedor, String>("emailProveedor"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<EmailProveedor, String>("descripcion"));
        colIDProveedor.setCellValueFactory(new PropertyValueFactory<EmailProveedor, Integer>("IDProveedor"));
    }

    public void seleccionarElementos() {
            txtIDEmailProveedor.setText(String.valueOf(((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getIDEmailProveedor()));
            txtEmailProveedor.setText((((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getEmailProveedor()));
            txtDescripcion.setText((((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getDescripcion()));
            cmbIDProveedor.getSelectionModel().select(buscarProveedores(((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getIDProveedor()));
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
    
    public ObservableList<EmailProveedor> getEmailProveedor() {
        ArrayList<EmailProveedor> lista = new ArrayList<EmailProveedor>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarEmailProveedor()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new EmailProveedor(resultado.getInt("IDEmailProveedor"),
                        resultado.getString("emailProveedor"),
                        resultado.getString("descripcion"),
                        resultado.getInt("IDProveedor")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmailProveedor = FXCollections.observableList(lista);
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
        txtIDEmailProveedor.setEditable(false);
        txtEmailProveedor.setEditable(false);
        txtDescripcion.setEditable(false);
        cmbIDProveedor.setDisable(true);
    }

    public void activarControles() {
        txtIDEmailProveedor.setEditable(true);
        txtEmailProveedor.setEditable(true);
        txtDescripcion.setEditable(true);
        cmbIDProveedor.setDisable(false);
    }

    public void limpiarControles() {
        txtIDEmailProveedor.clear();
        txtEmailProveedor.clear();
        txtDescripcion.clear();
        tblEmailProveedor.getSelectionModel().getSelectedItem();
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
        EmailProveedor registro = new EmailProveedor();
        registro.setIDEmailProveedor(Integer.parseInt(txtIDEmailProveedor.getText()));
        registro.setEmailProveedor((txtEmailProveedor.getText()));
        registro.setDescripcion((txtDescripcion.getText()));
        registro.setIDProveedor(((Proveedores) cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedor());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarEmailProveedor(?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDEmailProveedor());
            procedimiento.setString(2, registro.getEmailProveedor());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setInt(4, registro.getIDProveedor());
            procedimiento.execute();
            listaEmailProveedor.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarEmailProveedor(?,?,?,?)}");
            EmailProveedor registro = (EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem();
            registro.setEmailProveedor((txtEmailProveedor.getText()));
        registro.setDescripcion((txtDescripcion.getText()));
        registro.setIDProveedor(((Proveedores) cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedor());
            procedimiento.setInt(1, registro.getIDEmailProveedor());
            procedimiento.setString(2, registro.getEmailProveedor());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setInt(4, registro.getIDProveedor());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblEmailProveedor.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtIDEmailProveedor.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar un Email de Proveedor para editar");
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
                if (tblEmailProveedor.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Email de proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarEmailProveedor(?)}");
                            procedimiento.setInt(1, ((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getIDEmailProveedor());
                            procedimiento.execute();
                            listaEmailProveedor.remove(tblEmailProveedor.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un Email de proveedor para eliminar");
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

    public Button getBtnProveedores() {
        return btnProveedores;
    }

    public void setBtnProveedores(Button btnProveedores) {
        this.btnProveedores = btnProveedores;
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuK) {
            escenarioPrincipal.menuPrincipalView();
        }if (event.getSource() == btnProveedores) {
            escenarioPrincipal.menuProveedoresView();
        }
    }
}
