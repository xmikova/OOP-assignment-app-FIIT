package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.objectclasses.Customer;
import org.example.tools.OrderManager;
import org.example.objectclasses.Place;
import org.example.tools.UserManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Controller which handles the trip summary scene where all the information about created trip are shown to the user and they can accept it or reject it. It implements the Initializable interface in order to be able to show this info from the current order object.
 */
public class TripSummaryController implements Initializable {
    OrderManager orderManager = OrderManager.getInstance();
    UserManager<Customer> customerUserManager = UserManager.getInstance();
    public Label locationLabel;
    public Label childrenLabel;
    public Label petsLabel;
    public Label genderLabel;
    public Label cityCentreLabel;
    public Label minAgeLabel;
    public Label maxAgeLabel;
    public Hyperlink itineraryLabel;
    public Label expectedPriceLabel;
    public Label lengthLabel;
    public Button acceptButton;
    public Button rejectButton;

    /**
     * Method to display the information.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLocationLabel(locationContr);
        setChildrenLabel(childrenContr);
        setPetsLabel(petsContr);
        setGenderLabel(genderContr);
        setCityCentreLabel(cityCentreContr);
        setMinAgeLabel(minAgeContr);
        setMaxAgeLabel(maxAgeContr);
        setExpectedPriceLabel(expPriceContr);
        setLengthLabel(lengthContr);
    }

    private String locationContr = orderManager.getCurrentOrder().getLocation();
    private boolean childrenContr = orderManager.getCurrentOrder().isChildren();
    private boolean petsContr = orderManager.getCurrentOrder().isPets();
    private boolean genderContr = orderManager.getCurrentOrder().isMale();
    private boolean cityCentreContr = orderManager.getCurrentOrder().isCityCentre();
    private int minAgeContr = orderManager.getCurrentOrder().getMinAge();
    private int maxAgeContr = orderManager.getCurrentOrder().getMaxAge();
    private double expPriceContr = orderManager.getCurrentOrder().getExpectedPrice();
    private int lengthContr = orderManager.getCurrentOrder().getLength();

    public void setLocationLabel(String locationContr) {
        locationLabel.setText(locationContr);
    }

    public void setChildrenLabel(boolean childrenContr) {
        if (childrenContr){
            childrenLabel.setText("Yes");
        } else childrenLabel.setText("No");
    }

    public void setPetsLabel(boolean petsContr){
        if (petsContr){
            petsLabel.setText("Yes");
        } else petsLabel.setText("No");
    }

    public void setGenderLabel(boolean genderContr){
        if (genderContr){
            genderLabel.setText("Male");
        } else genderLabel.setText("Female");
    }

    public void setCityCentreLabel(boolean cityCentreContr){
        if (cityCentreContr){
            cityCentreLabel.setText("Yes");
        } else cityCentreLabel.setText("No");
    }

    public void setMinAgeLabel(int minAgeContr){
        minAgeLabel.setText(String.valueOf(minAgeContr));
    }

    public void setMaxAgeLabel(int maxAgeContr){
        maxAgeLabel.setText(String.valueOf(maxAgeContr));
    }

    public void setExpectedPriceLabel(double expPriceContr){
        expectedPriceLabel.setText(String.valueOf(expPriceContr));
    }

    public void setLengthLabel(int lengthContr){
        lengthLabel.setText(String.valueOf(lengthContr) + " days");
    }

    /**
     * Method that when user clicks on their itinerary, all the places they have chosen in previous step will pop up.
     */
    @FXML
    public void hyperLinkHandlerTripSummary(ActionEvent event) throws IOException {
        List<Place> selectedPlacesHyper = customerUserManager.getCurrentUser().getPreferences().getSelectedPlaces();
        VBox mainBox = new VBox();

        for (Place place : selectedPlacesHyper) {
            VBox infoBox = new VBox();
            infoBox.getChildren().addAll(
                    new Label("Place: " + place.getName())
            );
            mainBox.getChildren().add(infoBox);
        }

        mainBox.setStyle("-fx-font-size: 16px;");
        Scene scene = new Scene(mainBox);

        Stage stage = new Stage();
        stage.setTitle("Places itinerary");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to handle accepting the offer by customer.
     */
    @FXML
    public void acceptTheOffer(ActionEvent event) throws IOException{
        orderManager.getCurrentOrder().setStatus("Accepted by customer - pending");
        orderManager.getCurrentOrder().updateStatus(orderManager.getCurrentOrder().getId(),"Accepted by customer - pending");

        Parent ninethSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/afterLoginPageCustomer.fxml"));
        Scene ninethScene = new Scene(ninethSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(ninethScene);
    }

    /**
     * Method to handle rejecting the offer by customer.
     */
    @FXML
    public void rejectTheOffer(ActionEvent event) throws IOException{
        orderManager.getCurrentOrder().setStatus("Rejected by customer");
        orderManager.getCurrentOrder().updateStatus(orderManager.getCurrentOrder().getId(),"Rejected by customer");


        Parent ninethSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/afterLoginPageCustomer.fxml"));
        Scene ninethScene = new Scene(ninethSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(ninethScene);
    }

    /**
     * Method which handles going back to previous page, based on the chosen location.
     */
    @FXML
    private void goBackToPref2(ActionEvent event) throws IOException {
        if (orderManager.getCurrentOrder().getLocation() == "Madrid"){
            Parent seventhSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences2madrid.fxml"));
            Scene seventhScene = new Scene(seventhSceneRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(seventhScene);
            currentStage.show();
        }else if (orderManager.getCurrentOrder().getLocation() == "Valencia"){
            Parent seventhSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences2valencia.fxml"));
            Scene seventhScene = new Scene(seventhSceneRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(seventhScene);
            currentStage.show();
        }else if (orderManager.getCurrentOrder().getLocation() == "Barcelona"){
            Parent seventhSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences2barcelona.fxml"));
            Scene seventhScene = new Scene(seventhSceneRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(seventhScene);
            currentStage.show();
        }
    }
}
