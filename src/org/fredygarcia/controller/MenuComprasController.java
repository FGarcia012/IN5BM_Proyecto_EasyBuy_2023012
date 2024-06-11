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
import org.fredygarcia.bean.Compras;
import org.fredygarcia.db.Conexion;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class MenuComprasController implements Initializable {

    private Main escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, REPORTE, NULL
    }
    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<Compras> listaCompras;

    @FXML
    private TableView tblCompras;

    @FXML
    private TableColumn ColumnNumDocumento;

    @FXML
    private TableColumn ColumnFechaDocumento;

    @FXML
    private TableColumn ColumnDescripcion;

    @FXML
    private TableColumn columnTotalDocumento;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnMenuD;

    @FXML
    private TextField txtNumDocumento;

    @FXML
    private TextField txtFechaDocumento;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtTotalDocumento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }

    public void cargarDatos() {
        tblCompras.setItems(getCompras());
        ColumnNumDocumento.setCellValueFactory(new PropertyValueFactory<Compras, Integer>("numDocumento"));
        ColumnFechaDocumento.setCellValueFactory(new PropertyValueFactory<Compras, Integer>("fechaDocumento"));
        ColumnDescripcion.setCellValueFactory(new PropertyValueFactory<Compras, Integer>("descripcion"));
        columnTotalDocumento.setCellValueFactory(new PropertyValueFactory<Compras, Integer>("totalDocumento"));

    }

    public void seleccionarElementos() {

        txtNumDocumento.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getNumDocumento()));
        txtFechaDocumento.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getFechaDocumento()));
        txtDescripcion.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getDescripcion()));
        txtTotalDocumento.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getTotalDocumento()));

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
                if (tblCompras.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion de registro",
                            "Eliminar Compras", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarCompra(?)}");
                            procedimiento.setInt(1, ((Compras) tblCompras.getSelectionModel().getSelectedItem()).getNumDocumento());
                            procedimiento.execute();
                            listaCompras.remove(tblCompras.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar una compra para eliminar");
                }
        }
    }

    public void editar() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblCompras.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtNumDocumento.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe de seleccionar una compra para editar");
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

    public void guardar() {
        Compras registro = new Compras();
        registro.setNumDocumento(Integer.parseInt(txtNumDocumento.getText()));
        registro.setFechaDocumento(txtFechaDocumento.getText());
        registro.setDescripcion(txtDescripcion.getText());
        registro.setTotalDocumento(Double.parseDouble("0.00"));
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarCompra(?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getNumDocumento());
            procedimiento.setString(2, registro.getFechaDocumento());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setDouble(4, registro.getTotalDocumento());
            procedimiento.execute();
            listaCompras.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarCompra(?,?,?,?)}");
            Compras registro = (Compras) tblCompras.getSelectionModel().getSelectedItem();
            registro.setFechaDocumento(txtFechaDocumento.getText());
            registro.setDescripcion(txtDescripcion.getText());
            registro.setTotalDocumento(Double.parseDouble(txtTotalDocumento.getText()));
            procedimiento.setInt(1, registro.getNumDocumento());
            procedimiento.setString(2, registro.getFechaDocumento());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setDouble(4, registro.getTotalDocumento());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void desactivarControles() {
        txtNumDocumento.setEditable(false);
        txtFechaDocumento.setEditable(false);
        txtDescripcion.setEditable(false);
        txtTotalDocumento.setEditable(false);
    }

    public void activarControles() {
        txtNumDocumento.setEditable(true);
        txtFechaDocumento.setEditable(true);
        txtDescripcion.setEditable(true);
        txtTotalDocumento.setEditable(true);
    }

    public void limpiarControles() {
        txtNumDocumento.clear();
        txtFechaDocumento.clear();
        txtDescripcion.clear();
        txtTotalDocumento.clear();
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuD) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
}