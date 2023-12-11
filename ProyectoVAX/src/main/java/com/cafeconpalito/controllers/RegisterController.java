/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cafeconpalito.controllers;

import com.cafeconpalito.proyectovax.App;
import com.cafeconpalito.staticElements.MainView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ramiro
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField firstSurnameTextField;
    @FXML
    private TextField secondSurnameTextField;
    @FXML
    private DatePicker BirthDatePicker;
    @FXML
    private ComboBox<String> regionComboBox;
    
    @FXML
    private TextField emailTextField;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        regionComboBox.getItems().addAll("user", "developer");
    }    

    @FXML
    private void nextBtn(MouseEvent event) throws IOException {
        
        //Si todos los campos son correctos te deja avanzar si no es asi error!
        if (true) {
            MainView.main.setCenter(App.loadFXML("register_1"));
        } else {
            System.out.println("FALTAN DATOS!");
        }
        
        
    }

    
}
