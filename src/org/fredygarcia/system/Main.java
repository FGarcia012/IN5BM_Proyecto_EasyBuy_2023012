/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fredygarcia.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fredygarcia.controller.MenuCargoEmpleadoController;
import org.fredygarcia.controller.MenuClienteController;
import org.fredygarcia.controller.MenuComprasController;
import org.fredygarcia.controller.MenuDetalleCompraController;
import org.fredygarcia.controller.MenuDetalleFacturaController;
import org.fredygarcia.controller.MenuEmailProveedorController;
import org.fredygarcia.controller.MenuEmpleadosController;
import org.fredygarcia.controller.MenuFacturaController;
import org.fredygarcia.controller.MenuPrincipalController;
import org.fredygarcia.controller.MenuProductosController;
import org.fredygarcia.controller.MenuProveedoresController;
import org.fredygarcia.controller.MenuTipoProductosController;
import org.fredygarcia.controller.MenuTelefonoProveedorController;
import org.fredygarcia.controller.PrograViewController;

/**
 *
 * @author alexa
 */
public class Main extends Application {

    private Stage escenarioPrincipal;
    private Scene escena;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("EasyBuy");
        menuPrincipalView();
        escenarioPrincipal.getIcons().add(new Image("/org/fredygarcia/images/Isotipo.png"));
        escenarioPrincipal.show();
        FXMLLoader.load(getClass().getResource("/org/fredygarcia/view/MenuPrincipalView.fxml"));
    }

    public Initializable cambiarEscena(String fxmlname, int width, int height) throws Exception {
        Initializable resultado;
        FXMLLoader loader = new FXMLLoader();
        InputStream file = Main.class.getResourceAsStream("/org/fredygarcia/view/" + fxmlname);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/org/fredygarcia/view/" + fxmlname));

        escena = new Scene((AnchorPane) loader.load(file), width, height);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();

        resultado = (Initializable) loader.getController();

        return resultado;
    }

    public void menuPrincipalView() {
        try {
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController) cambiarEscena("MenuPrincipalView.fxml", 615, 564);
            menuPrincipalView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuClienteView() {
        try {
            MenuClienteController menuClienteView = (MenuClienteController) cambiarEscena("MenuClienteView.fxml", 853, 499);
            menuClienteView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuProveedoresView() {
        try {
            MenuProveedoresController menuProveedoresView = (MenuProveedoresController) cambiarEscena("MenuProveedoresView.fxml", 1170, 650);
            menuProveedoresView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuCargoEmpleadosView() {
        try {
            MenuCargoEmpleadoController menuCargoEmpleadosView = (MenuCargoEmpleadoController) cambiarEscena("MenuCargoEmpleadosView.fxml", 750, 420);
            menuCargoEmpleadosView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuComprasView() {
        try {
            MenuComprasController menuComprasView = (MenuComprasController) cambiarEscena("MenuComprasView.fxml", 750, 425);
            menuComprasView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuTipoProductosView() {
        try {
            MenuTipoProductosController menuTipoProductosView = (MenuTipoProductosController) cambiarEscena("MenuTipoProductosView.fxml", 750, 425);
            menuTipoProductosView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuProductosView() {
        try {
            MenuProductosController menuTipoProductosView = (MenuProductosController) cambiarEscena("MenuProductosView.fxml", 1200, 670);
            menuTipoProductosView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuEmpleadosView() {
        try {
            MenuEmpleadosController menuEmpleadosView = (MenuEmpleadosController) cambiarEscena("MenuEmpleadosView.fxml", 750, 450);
            menuEmpleadosView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuFacturaView() {
        try {
            MenuFacturaController menuFacturaView = (MenuFacturaController) cambiarEscena("MenuFacturaView.fxml", 750, 450);
            menuFacturaView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuDetalleFacturaView() {
        try{
        MenuDetalleFacturaController menuDetalleFacturaView = (MenuDetalleFacturaController) cambiarEscena("MenuDetalleFacturaView.fxml", 750, 450);
        menuDetalleFacturaView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void menuDetalleCompraView() {
        try {
            MenuDetalleCompraController menuDetalleCompraView = (MenuDetalleCompraController) cambiarEscena("MenuDetalleCompraView.fxml", 750, 450);
            menuDetalleCompraView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuTelefonoProveedorView() {
        try{
            MenuTelefonoProveedorController menuTelefonoProveedorView = (MenuTelefonoProveedorController)cambiarEscena("MenuTelefonoProveedorView.fxml", 750, 450);
            menuTelefonoProveedorView.setEscenarioPrincipal(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void menuEmailProveedorView() {
        try{
            MenuEmailProveedorController menuEmailProveedorView = (MenuEmailProveedorController) cambiarEscena("MenuEmailProveedorView.fxml", 750, 450);
            menuEmailProveedorView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void prograView() {
        try {
            PrograViewController prograview = (PrograViewController) cambiarEscena("PrograView.fxml", 600, 335);
            prograview.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
