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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.fredygarcia.bean.Clientes;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuClienteController implements Initializable {

    private Main escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<Clientes> listaClientes;

    @FXML
    private TableView TablaClientes;

    @FXML
    private TableColumn ColumnIDCliente;

    @FXML
    private TableColumn ColumNombreClientes;

    @FXML
    private TableColumn ColumnApellidosClientes;

    @FXML
    private TableColumn ColumnNit;

    @FXML
    private TableColumn ColumnTelefonosClientes;

    @FXML
    private TableColumn ColumnDireccionClientes;

    @FXML
    private TableColumn ColumnCorreoClientes;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenu;

    @FXML
    private TextField txtIDClientes;

    @FXML
    private TextField txtNombreClientes;

    @FXML
    private TextField txtApellidoClientes;

    @FXML
    private TextField txtNit;

    @FXML
    private TextField txtTelefonoClientes;

    @FXML
    private TextField txtDireccionClientes;

    @FXML
    private TextField txtCorreoClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }

    public void cargarDatos() {
        TablaClientes.setItems(getClientes());
        ColumnIDCliente.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("IDClientes"));
        ColumNombreClientes.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("nombreClientes"));
        ColumnApellidosClientes.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("apellidosClientes"));
        ColumnNit.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("NITClientes"));
        ColumnTelefonosClientes.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("telefonoClientes"));
        ColumnDireccionClientes.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("direccionClientes"));
        ColumnCorreoClientes.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("correoClientes"));
    }

    public void seleccionarElementos() {
        txtIDClientes.setText(String.valueOf(((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getIDClientes()));
        txtNombreClientes.setText((((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getNombreClientes()));
        txtApellidoClientes.setText((((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getApellidosClientes()));
        txtNit.setText((((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getNITClientes()));
        txtTelefonoClientes.setText((((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getTelefonoClientes()));
        txtDireccionClientes.setText((((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getDireccionClientes()));
        txtCorreoClientes.setText((((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getCorreoClientes()));
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

    public void Agregar() {
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
                cargarDatos();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                break;
        }
    }

    public void guardar() {
        Clientes registro = new Clientes();
        registro.setIDClientes(Integer.parseInt(txtIDClientes.getText()));
        registro.setNombreClientes(txtNombreClientes.getText());
        registro.setApellidosClientes(txtApellidoClientes.getText());
        registro.setNITClientes(txtNit.getText());
        registro.setTelefonoClientes(txtTelefonoClientes.getText());
        registro.setDireccionClientes(txtDireccionClientes.getText());
        registro.setCorreoClientes(txtCorreoClientes.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarClientes(?, ?, ?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDClientes());
            procedimiento.setString(2, registro.getNombreClientes());
            procedimiento.setString(3, registro.getApellidosClientes());
            procedimiento.setString(4, registro.getNITClientes());
            procedimiento.setString(5, registro.getTelefonoClientes());
            procedimiento.setString(6, registro.getDireccionClientes());
            procedimiento.setString(7, registro.getCorreoClientes());
            procedimiento.execute();
            listaClientes.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminar() {
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
                if (TablaClientes.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Clientes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarClientes(?)}");
                            procedimiento.setInt(1, ((Clientes) TablaClientes.getSelectionModel().getSelectedItem()).getIDClientes());
                            procedimiento.execute();
                            listaClientes.remove(TablaClientes.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un cliente para eliminar");
                }
        }
    }

    public void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (TablaClientes.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtIDClientes.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar un cliente para editar");
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
    
    public void reporte() {
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
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarClientes(?,?,?,?,?,?,?)}");
            Clientes registro = (Clientes) TablaClientes.getSelectionModel().getSelectedItem();
            registro.setNombreClientes(txtNombreClientes.getText());
            registro.setApellidosClientes(txtApellidoClientes.getText());
            registro.setNITClientes(txtNit.getText());
            registro.setTelefonoClientes(txtTelefonoClientes.getText());
            registro.setDireccionClientes(txtDireccionClientes.getText());
            registro.setCorreoClientes(txtCorreoClientes.getText());
            procedimiento.setInt(1, registro.getIDClientes());
            procedimiento.setString(2, registro.getNombreClientes());
            procedimiento.setString(3, registro.getApellidosClientes());
            procedimiento.setString(4, registro.getNITClientes());
            procedimiento.setString(5, registro.getTelefonoClientes());
            procedimiento.setString(6, registro.getDireccionClientes());
            procedimiento.setString(7, registro.getCorreoClientes());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desactivarControles() {
        txtIDClientes.setEditable(false);
        txtNombreClientes.setEditable(false);
        txtApellidoClientes.setEditable(false);
        txtDireccionClientes.setEditable(false);
        txtNit.setEditable(false);
        txtTelefonoClientes.setEditable(false);
        txtCorreoClientes.setEditable(false);
    }

    public void activarControles() {
        txtIDClientes.setEditable(true);
        txtNombreClientes.setEditable(true);
        txtApellidoClientes.setEditable(true);
        txtDireccionClientes.setEditable(true);
        txtNit.setEditable(true);
        txtTelefonoClientes.setEditable(true);
        txtCorreoClientes.setEditable(true);
    }

    public void limpiarControles() {
        txtIDClientes.clear();
        txtNombreClientes.clear();
        txtApellidoClientes.clear();
        txtDireccionClientes.clear();
        txtNit.clear();
        txtTelefonoClientes.clear();
        txtCorreoClientes.clear();
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenu) {
            escenarioPrincipal.menuPrincipalView();
        }
    }

}
