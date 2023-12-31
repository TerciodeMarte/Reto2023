/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cafeconpalito.controllers;

import com.cafeconpalito.proyectovax.App;
import com.cafeconpalito.proyectovax.EntryPoint;
import com.cafeconpalito.staticElements.CheckURLImg;
import com.cafeconpalito.staticElements.ConectionBBDD;
import com.cafeconpalito.staticElements.MainView;
import com.cafeconpalito.userLogedData.UserLogedInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author CafeConPalito
 */
public class PanelIzquierdoGeneralController implements Initializable {

    @FXML
    private Button allGamesBtn;
    @FXML
    private Button myGamesBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Label alias;
    @FXML
    private ImageView avatarUser;

    private Image imgdefault;

    /**
     * Initializes the controller class. Si el usuario esta logeado cambia el
     * Texto de Login a Log Out. , Actualiza la informacion del usuario en el
     * panel. , Comprueba que se puede acceder a la imagen y si es asi la carga.
     * sino mantiene la imagen por defecto
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgdefault = avatarUser.getImage();
        if (UserLogedInfo.isUserIsLoged()) {
            //CARGAR INFO USUARIO
            loginBtn.setText("Log Out");
            //Cargar Alias

            alias.setText(UserLogedInfo.getAlias());
            //Cargar Avatar

            String urlavatar = "http://" + EntryPoint.getServerIP() + ":80" + EntryPoint.rutaImgUser + UserLogedInfo.getUsuarioUrlImagen();
            if (CheckURLImg.exists(urlavatar)) {
                avatarUser.setImage(new Image(urlavatar));
            }
        }

    }

    /**
     * Accion del Boton Strore cambia la vista a la tienda.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToStore(ActionEvent event) throws IOException {

        if (ConectionBBDD.getEm() == null) {
            MainView.main.setCenter(App.loadFXML("help"));
        } else {
            MainView.main.setCenter(App.loadFXML("store"));
        }

    }

    /**
     * Accion del Boton My Games. Si el usuario esta logueado cambia la vista a
     * juegos. Si el usuario no esta logueado cambia la vista a login.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToLibrary(ActionEvent event) throws IOException {

        if (ConectionBBDD.getEm() == null) {
            MainView.main.setCenter(App.loadFXML("help"));
        } else {
            if (UserLogedInfo.isUserIsLoged()) {
                MainView.main.setCenter(App.loadFXML("library"));
            } else {
                MainView.main.setCenter(App.loadFXML("login"));
            }
        }
    }

    /**
     * Accion del Boton Login / Logout.
     *
     * Si el usuario no esta logueado cambia la vista a loguear.
     *
     * Si el usuario ya esta logueado lo desloguea limpiando la informacion del
     * usuario y modificando el texto del boton.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToLogIn(ActionEvent event) throws IOException {

        if (ConectionBBDD.getEm() == null) {
            MainView.main.setCenter(App.loadFXML("help"));
        } else {
            if (UserLogedInfo.isUserIsLoged()) {
                //LOG OUT
                UserLogedInfo.logoutUser();
                MainView.main.setCenter(App.loadFXML("store"));
                alias.setText("@guest");
                loginBtn.setText("Log In");
                System.out.println(imgdefault.getUrl());
                avatarUser.setImage(imgdefault);
            } else {
                //LOG IN
                MainView.main.setCenter(App.loadFXML("login"));
            }
        }
    }

    /**
     * Accion del Boton Setings.
     *
     * Si el usuario esta logueado cambia la vista a Setings. Si el usuario no
     * esta logueado cambia la vista al login.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToSettings(ActionEvent event) throws IOException {

        if (ConectionBBDD.getEm() == null) {
            MainView.main.setCenter(App.loadFXML("help"));
        } else {
            if (UserLogedInfo.isUserIsLoged()) {
                //System.out.println("el usuario esta logeado");
            } else {
                //System.out.println("el usuaroio no esta logueado");
                MainView.main.setCenter(App.loadFXML("login"));
            }
        }

    }

    /**
     * Accion del Boton Help.
     *
     * cambia la vista a Help.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToHelp(ActionEvent event) throws IOException {
        MainView.main.setCenter(App.loadFXML("help"));
    }

}
