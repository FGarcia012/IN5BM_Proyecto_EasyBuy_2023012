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
import org.fredygarcia.bean.Proveedores;
import org.fredygarcia.bean.TelefonoProveedor;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

public class MenuTelefonoProveedorController implements Initializable {
    
    private Main escenarioPrincipal;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<TelefonoProveedor> listaTelefonoProveedor;
    private ObservableList<Proveedores> listaProveedores;
    
    @FXML
    private TableView tblTelefonoProveedor;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuJ;
    
    @FXML
    private Button btnProveedores;

    @FXML
    private TextField txtIDTelefonoProveedor;

    @FXML
    private TextField txtNumeroPrincipal;

    @FXML
    private TextField txtNumeroSecundario;

    @FXML
    private TextField txtObservaciones;

    @FXML
    private ComboBox cmbIDProveedor;

    @FXML
    private TableColumn colIDTelefonoProveedor;

    @FXML
    private TableColumn colNumeroPrincipal;

    @FXML
    private TableColumn colNumeroSecundario;

    @FXML
    private TableColumn colObservaciones;

    @FXML
    private TableColumn colIDProveedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbIDProveedor.setItems(getProveedores());
    }
    
    public void cargarDatos() {
        tblTelefonoProveedor.setItems(getTelefonoProveedor());
        colIDTelefonoProveedor.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, Integer>("IDTelefonoProveedor"));
        colNumeroPrincipal.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, String>("numeroPrincipal"));
        colNumeroSecundario.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, String>("numeroSecundario"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, String>("observaciones"));
        colIDProveedor.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, Integer>("IDProveedor"));
    }

    public void seleccionarElementos() {
        if (tblTelefonoProveedor.getSelectionModel().getSelectedItem() != null) {
            txtIDTelefonoProveedor.setText(String.valueOf(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getIDTelefonoProveedor()));
            txtNumeroPrincipal.setText((((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getNumeroPrincipal()));
            txtNumeroSecundario.setText((((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getNumeroSecundario()));
            txtObservaciones.setText((((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getObservaciones()));
            cmbIDProveedor.getSelectionModel().select(buscarProveedores(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getIDProveedor()));
        } else {
        }
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
    
    public ObservableList<TelefonoProveedor> getTelefonoProveedor() {
        ArrayList<TelefonoProveedor> lista = new ArrayList<TelefonoProveedor>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarTelefonoProveedor()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new TelefonoProveedor(resultado.getInt("IDTelefonoProveedor"),
                        resultado.getString("numeroPrincipal"),
                        resultado.getString("numeroSecundario"),
                        resultado.getString("observaciones"),
                        resultado.getInt("IDProveedor")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTelefonoProveedor = FXCollections.observableList(lista);
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
        txtIDTelefonoProveedor.setEditable(false);
        txtNumeroPrincipal.setEditable(false);
        txtNumeroSecundario.setEditable(false);
        txtObservaciones.setEditable(false);
        cmbIDProveedor.setDisable(true);
    }

    public void activarControles() {
        txtIDTelefonoProveedor.setEditable(true);
        txtNumeroPrincipal.setEditable(true);
        txtNumeroSecundario.setEditable(true);
        txtObservaciones.setEditable(true);
        cmbIDProveedor.setDisable(false);
    }

    public void limpiarControles() {
        txtIDTelefonoProveedor.clear();
        txtNumeroPrincipal.clear();
        txtNumeroSecundario.clear();
        txtObservaciones.clear();
        tblTelefonoProveedor.getSelectionModel().getSelectedItem();
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
        TelefonoProveedor registro = new TelefonoProveedor();
        registro.setIDTelefonoProveedor(Integer.parseInt(txtIDTelefonoProveedor.getText()));
        registro.setNumeroPrincipal((txtNumeroPrincipal.getText()));
        registro.setNumeroSecundario((txtNumeroSecundario.getText()));
        registro.setObservaciones((txtObservaciones.getText()));
        registro.setIDProveedor(((Proveedores) cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedor());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarTelefonoProveedor(?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDTelefonoProveedor());
            procedimiento.setString(2, registro.getNumeroPrincipal());
            procedimiento.setString(3, registro.getNumeroSecundario());
            procedimiento.setString(4, registro.getObservaciones());
            procedimiento.setInt(5, registro.getIDProveedor());
            procedimiento.execute();
            listaTelefonoProveedor.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarTelefonoProveedor(?,?,?,?,?)}");
            TelefonoProveedor registro = (TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem();
            registro.setNumeroPrincipal((txtNumeroPrincipal.getText()));
            registro.setNumeroSecundario((txtNumeroSecundario.getText()));
            registro.setObservaciones((txtObservaciones.getText()));
            registro.setIDProveedor(((Proveedores) cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedor());
            procedimiento.setInt(1, registro.getIDTelefonoProveedor());
            procedimiento.setString(2, registro.getNumeroPrincipal());
            procedimiento.setString(3, registro.getNumeroSecundario());
            procedimiento.setString(4, registro.getObservaciones());
            procedimiento.setInt(5, registro.getIDProveedor());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblTelefonoProveedor.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtIDTelefonoProveedor.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar un Telefono de Proveedor para editar");
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
                if (tblTelefonoProveedor.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Telefono de proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarTelefonoProveedor(?)}");
                            procedimiento.setInt(1, ((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getIDTelefonoProveedor());
                            procedimiento.execute();
                            listaTelefonoProveedor.remove(tblTelefonoProveedor.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un Telefono de proveedor para eliminar");
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
        if (event.getSource() == btnMenuJ) {
            escenarioPrincipal.menuPrincipalView();
        }if (event.getSource() == btnProveedores) {
            escenarioPrincipal.menuProveedoresView();
        }
    }
}
