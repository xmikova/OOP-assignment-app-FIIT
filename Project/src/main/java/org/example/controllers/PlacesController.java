package org.example.controllers;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.objectclasses.Customer;
import org.example.objectclasses.Place;
import org.example.tools.OrderManager;
import org.example.tools.UserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A superclass which has the methods essential for displaying the places in each location.
 */
public class PlacesController {
    OrderManager orderManager = OrderManager.getInstance();
    UserManager<Customer> customerUserManager = UserManager.getInstance();
    List<Place> allPlaces = Place.getPlaces();
    public VBox checkboxContainerRestaurants;
    public VBox checkboxContainerSights;
    private double pricesForPlaces = 0;

    /**
     * Method which handles that for each location, each place is a clickable hyperlink which displays information about the place after clicking on it.
     */
    public void hyperLinkHandler(ActionEvent event) throws IOException {
        Hyperlink clickedHyperlink = (Hyperlink) event.getSource();
        String placeName = clickedHyperlink.getId();
        Place tempplace = new Place();

        for (Place place : allPlaces){
            if (place.getId().equals(placeName)){
                tempplace.setName(place.getName());
                tempplace.setDescription(place.getDescription());
                tempplace.setPrice(place.getPrice());
                break;
            }
        }

        VBox infoBox = new VBox();
        infoBox.getChildren().addAll(
                new Label("Name: " + tempplace.getName()),
                new Label("Description: " + tempplace.getDescription()),
                new Label("Price: " + tempplace.getPrice())
        );

        infoBox.setStyle("-fx-font-size: 16px;");
        Scene infoScene = new Scene(infoBox);
        Stage stage = new Stage();
        stage.setTitle("Information about place");
        stage.setScene(infoScene);
        stage.show();
    }

    /**
     * Method that handles retrieving the chosen places and setting them to customer preferences.
     */
    public void checkBoxesSetSelectedPlaces(ActionEvent event) throws IOException {
        Customer thiscustomer = customerUserManager.getCurrentUser();
        List<Place> selected = new ArrayList<>();
        for (Node node : checkboxContainerRestaurants.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String id = checkBox.getId();
                    for (Place place : allPlaces){
                        if (place.getId2().equals(id)){
                            selected.add(place);
                        }
                    }
                }
            }
        }

        for (Node node : checkboxContainerSights.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String id = checkBox.getId();
                    for (Place place : allPlaces){
                        if (place.getId2().equals(id)){
                            selected.add(place);
                            pricesForPlaces += place.getPrice();
                        }
                    }
                }
            }
        }
        thiscustomer.getPreferences().setSelectedPlaces(selected);

        List<Place> selectedPlaces = thiscustomer.getPreferences().getSelectedPlaces();
        Gson gson = new Gson();
        String jsonPlacesString = gson.toJson(selectedPlaces);

        orderManager.getCurrentOrder().setExpectedPrice(orderManager.getCurrentOrder().getExpectedPrice() + pricesForPlaces);
        orderManager.getCurrentOrder().setSelectedPlaces(jsonPlacesString);
        orderManager.getCurrentOrder().save();

        Parent eighthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/tripsummarycustomer.fxml"));
        Scene eighthScene = new Scene(eighthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(eighthScene);
        currentStage.show();
    }

    /**
     * Method that handles clicking on the go back button with arrow.
     */
    public void goBackToPref1(ActionEvent event) throws IOException {
        Parent sixthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences1.fxml"));
        Scene sixthScene = new Scene(sixthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(sixthScene);
    }
}
