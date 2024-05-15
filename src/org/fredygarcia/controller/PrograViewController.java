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
import javafx.scene.control.Button;
import org.fredygarcia.system.Main;

/**
 *
 * @author alexa
 */
public class PrograViewController implements Initializable{
    public Main escenarioPrincipal;
    
    @FXML
    private Button btnRegresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public Button getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(Button btnRegresar) {
        this.btnRegresar = btnRegresar;
    }
    
    @FXML
    void handleButtonAction(ActionEvent event) throws Exception {
         if (event.getSource() == btnRegresar)
           escenarioPrincipal.menuPrincipalView();
            
        }
    
}
