package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;


/**
 * A class which inherits from PlacesController class and handles the chosen places in Madrid by a customer and sets it into the array list of place preferences which is an attribute of Customer.
 */
public class MadridPlacesController extends PlacesController {
    public Hyperlink Botin;
    public Hyperlink Diverxo;
    public Hyperlink Casamono;
    public Hyperlink Elcluballard;
    public Hyperlink Streetxo;
    public Hyperlink tenconten;
    public Hyperlink casalucio;
    public Hyperlink Lavaca;
    public Hyperlink Ramenkagura;
    public Hyperlink Latasqueria;
    public Hyperlink Madridpalace;
    public Hyperlink pradomuseum;
    public Hyperlink reinasofia;
    public Hyperlink granvia;
    public Hyperlink santiagobernabeu;
    public Hyperlink plazamayor;
    public Hyperlink mercadodesanmiguel;
    public Hyperlink puertadesol;
    public Hyperlink almudena;
    public Hyperlink retiropark;
    public VBox checkboxContainerRestaurants;
    public VBox checkboxContainerSights;
    public CheckBox Botin2;
    public CheckBox Casamono2;
    public CheckBox Elcluballard2;
    public CheckBox Diverxo2;
    public CheckBox tenconten2;
    public CheckBox Streetxo2;
    public CheckBox casalucio2;
    public CheckBox Lavaca2;
    public CheckBox Latasqueria2;
    public CheckBox Ramenkagura2;
    public CheckBox Madridpalace2;
    public CheckBox pradomuseum2;
    public CheckBox santiagobernabeu2;
    public CheckBox retiropark2;
    public CheckBox plazamayor2;
    public CheckBox reinasofia2;
    public CheckBox mercadodesanmiguel2;
    public CheckBox granvia2;
    public CheckBox almudena2;
    public CheckBox puertadelsol2;
    public Button offerButton;

    public void hyperLinkHandler(ActionEvent event) throws IOException {
        super.hyperLinkHandler(event);
    }

    @FXML
    public void checkBoxesSetSelectedPlaces(ActionEvent event) throws IOException {
        super.checkBoxesSetSelectedPlaces(event);
    }

    @FXML
    public void goBackToPref1(ActionEvent event) throws IOException {
        super.goBackToPref1(event);
    }
}
