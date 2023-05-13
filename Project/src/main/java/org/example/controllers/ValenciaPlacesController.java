package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * A class which inherits from PlacesController class and handles the chosen places in Valencia by a customer and sets it into the array list of place preferences which is an attribute of Customer.
 */

public class ValenciaPlacesController extends PlacesController {
    public Hyperlink casacaramela;
    public Hyperlink ricardcamarena;
    public Hyperlink lapepica;
    public Hyperlink cerveceriamaipi;
    public Hyperlink ricardcamarenacolon;
    public Hyperlink lariua;
    public Hyperlink navarro;
    public Hyperlink elpoblet;
    public Hyperlink lalluna;
    public Hyperlink lamaritimo;
    public Hyperlink cityofaas;
    public Hyperlink centralmarket;
    public Hyperlink lonja;
    public Hyperlink turiagardens;
    public Hyperlink valenciacathedral;
    public Hyperlink torresdeserranos;
    public Hyperlink albufera;
    public Hyperlink mercadocolon;
    public Hyperlink bioparc;
    public Hyperlink oceanografic;
    public VBox checkboxContainerRestaurants;
    public CheckBox casacarmela2;
    public CheckBox ricardcamarena2;
    public CheckBox lapepica2;
    public CheckBox cerveceriamaipi2;
    public CheckBox lariua2;
    public CheckBox ricardcamarenacolon2;
    public CheckBox navarro2;
    public CheckBox elpoblet2;
    public CheckBox lamaritimo2;
    public CheckBox lalluna2;
    public VBox checkboxContainerSights;
    public CheckBox cityofaas2;
    public CheckBox centralmarket2;
    public CheckBox torresdeserranos2;
    public CheckBox lonja2;
    public CheckBox albufera2;
    public CheckBox turiagardens2;
    public CheckBox mercadocolon2;
    public CheckBox valenciacathedral2;
    public CheckBox oceanografic2;
    public CheckBox bioparc2;

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
