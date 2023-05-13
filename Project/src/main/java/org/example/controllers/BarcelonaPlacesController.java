package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * A class which inherits from PlacesController class and handles the chosen places in Barcelona by a customer and sets it into the array list of place preferences which is an attribute of Customer.
 */
public class BarcelonaPlacesController extends PlacesController {
    public Hyperlink tickets;
    public Hyperlink elspescadors;
    public Hyperlink disfrutar;
    public Hyperlink canculleretes;
    public Hyperlink brugarol;
    public Hyperlink laparadeta;
    public Hyperlink ticketslabodega;
    public Hyperlink pacomeralgo;
    public Hyperlink casacalvet;
    public Hyperlink barcanete;
    public Hyperlink sagradafamilia;
    public Hyperlink parkguell;
    public Hyperlink casabatllo;
    public Hyperlink larambla;
    public Hyperlink barcelonetabeach;
    public Hyperlink campnou;
    public Hyperlink musicacatalana;
    public Hyperlink casamila;
    public Hyperlink montjuic;
    public Hyperlink casavicens;
    public VBox checkboxContainerRestaurants;
    public CheckBox tickets2;
    public CheckBox elspescadors2;
    public CheckBox disfrutar2;
    public CheckBox canculleretes2;
    public CheckBox laparadeta2;
    public CheckBox brugarol2;
    public CheckBox ticketslabodega2;
    public CheckBox pacomeralgo2;
    public CheckBox barcanete2;
    public CheckBox casacalvet2;
    public VBox checkboxContainerSights;
    public CheckBox sagradafamilia2;
    public CheckBox parkguell2;
    public CheckBox campnou2;
    public CheckBox casabatllo2;
    public CheckBox musicacatalana2;
    public CheckBox larambla2;
    public CheckBox casamila2;
    public CheckBox barcelonetabeach2;
    public CheckBox casavicens2;
    public CheckBox montjuic2;

    @FXML
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
