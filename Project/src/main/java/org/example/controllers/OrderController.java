package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.objectclasses.Customer;
import org.example.objectclasses.CustomerPreferences;
import org.example.exceptions.OrderExceptions;
import org.example.objectclasses.Order;
import org.example.tools.AfterLoginScreen;
import org.example.tools.OrderManager;
import org.example.tools.UserManager;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * A class for handling the order interaction from customer's side. It implements the Initializable interface in order to achieve displaying the Hello, (customer's first name) in the initialize method.
 */
public class OrderController implements Initializable, AfterLoginScreen {
    UserManager<Customer> customerUserManager = UserManager.getInstance();
    OrderManager orderManager = OrderManager.getInstance();
    @FXML
    private Text userFirstNameID;

    private String customerFirstName = customerUserManager.getCurrentUser().getFirstName();

    public void setHelloUser(String userFirstName){
        userFirstNameID.setText(userFirstName);
    }

    @FXML
    private RadioButton Madrid;

    @FXML
    private RadioButton Barcelona;

    @FXML
    private RadioButton Valencia;

    @FXML
    private ChoiceBox<String> genderOfLocal;

    @FXML
    private CheckBox childrenLocal;

    @FXML
    private CheckBox petsLocal;

    @FXML
    private CheckBox cityCentreLocal;

    @FXML
    private TextField minAge;

    @FXML
    private TextField maxAge;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField length;

    public OrderController() {
    }

    /**
     * A method for handling clicking the create new order button. It goes to the scene where customer sets their preferences on local.
     */
    @FXML
    public void createNewOrderButton(ActionEvent event) throws IOException {
        Parent sixthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences1.fxml"));
        Scene sixthScene = new Scene(sixthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(sixthScene);
        Order newOrder = new Order();
        orderManager.setCurrentOrder(newOrder);
    }

    /**
     * A method that collects the information which was put by customer in the GUI and sets their preferences to their preferences attribute.
     */
    @FXML
    public void setPreferencesOnLocal(){
        CustomerPreferences preferences = new CustomerPreferences();

        orderManager.getCurrentOrder().setCustomerFirstName(customerUserManager.getCurrentUser().getFirstName());
        int age = customerUserManager.getCurrentUser().getAge();
        orderManager.getCurrentOrder().setCustomerAge(Integer.toString(age));
        orderManager.getCurrentOrder().setCustomerUsername(customerUserManager.getCurrentUser().getUsername());


        if (this.Madrid.isSelected()){
            preferences.setLocation("Madrid");
        }else if (this.Barcelona.isSelected()){
            preferences.setLocation("Barcelona");
        } else if (this.Valencia.isSelected()){
            preferences.setLocation("Valencia");
        }

        orderManager.getCurrentOrder().setLocation(preferences.getLocation());

        if ("Female".equals(this.genderOfLocal.getValue())){
            preferences.setMale(false);
        }else if("Male".equals(this.genderOfLocal.getValue())) {
            preferences.setMale(true);
        }

        orderManager.getCurrentOrder().setMale(preferences.isMale());

        if (this.childrenLocal.isSelected()){
            preferences.setChildren(true);
        }else preferences.setChildren(false);

        orderManager.getCurrentOrder().setChildren(preferences.isChildren());

        if (this.petsLocal.isSelected()){
            preferences.setPets(true);
        }else preferences.setPets(false);

        orderManager.getCurrentOrder().setPets(preferences.isPets());

        if (this.cityCentreLocal.isSelected()){
            preferences.setCityCentre(true);
        }else preferences.setCityCentre(false);

        orderManager.getCurrentOrder().setCityCentre(preferences.isCityCentre());

        preferences.setMinAge(Integer.parseInt(this.minAge.getText()));
        orderManager.getCurrentOrder().setMinAge(preferences.getMinAge());

        preferences.setMaxAge(Integer.parseInt(this.maxAge.getText()));
        orderManager.getCurrentOrder().setMaxAge(preferences.getMaxAge());

        preferences.setMaxPricePerDay(Integer.parseInt(this.maxPrice.getText()));
        orderManager.getCurrentOrder().setMaxPricePerDay(preferences.getMaxPricePerDay());

        preferences.setLength(Integer.parseInt(this.length.getText()));
        orderManager.getCurrentOrder().setLength(preferences.getLength());

        Customer customer = customerUserManager.getCurrentUser();
        double expectedPrice = (double)(preferences.getLength()) * (double)(preferences.getMaxPricePerDay());
        orderManager.getCurrentOrder().setExpectedPrice(expectedPrice);
        customer.setPreferences(preferences);
    }

    /**
     * After clicking on continue, this class sets the preferences and handles displaying another scene with places to choose from based on which location was selected.
     */
    @FXML
    public void continueInOrderButton(ActionEvent event) {
        try{
            if (!this.Madrid.isSelected() && !this.Barcelona.isSelected() &&!this.Valencia.isSelected()){
                throw new OrderExceptions.NoLocationChosen();
            }

            if (this.genderOfLocal.getValue() == null || this.minAge.getText().isEmpty() || this.maxAge.getText().isEmpty() || this.maxPrice.getText().isEmpty() || this.length.getText().isEmpty()) {
                throw new OrderExceptions.BlankFieldsExceptionOrder();
            }

            if (!isNumber(this.minAge.getText()) || !isNumber(this.maxAge.getText()) || !isNumber(this.maxPrice.getText()) || !isNumber(this.length.getText())){
                throw new OrderExceptions.InvalidNumberInput();
            }

            setPreferencesOnLocal();

            if (this.Madrid.isSelected()){
                Parent seventhSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences2madrid.fxml"));
                Scene seventhScene = new Scene(seventhSceneRoot);
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(seventhScene);
                currentStage.show();
            }else if (this.Barcelona.isSelected()){
                Parent seventhSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences2barcelona.fxml"));
                Scene seventhScene = new Scene(seventhSceneRoot);
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(seventhScene);
                currentStage.show();
            }else if (this.Valencia.isSelected()){
                Parent seventhSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/customerpreferences2valencia.fxml"));
                Scene seventhScene = new Scene(seventhSceneRoot);
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(seventhScene);
                currentStage.show();
            }
        } catch (OrderExceptions.NoLocationChosen e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order error");
            alert.setHeaderText("Order error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (OrderExceptions.BlankFieldsExceptionOrder e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order error");
            alert.setHeaderText("Order error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (OrderExceptions.InvalidNumberInput e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order error");
            alert.setHeaderText("Order error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException | IllegalArgumentException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Order error");
            alert.setHeaderText("Order error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * A helper method for throwing exceptions when invalid input is in a field where a number should be put.
     */
    private static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method which displays the orders of current user logged in and their status.
     */
    public void showOrders() {
        List<Order> orders = getOrdersForCurrentUser();

        VBox mainBox = new VBox();
        mainBox.setPrefWidth(400);
        mainBox.setStyle("-fx-font-size: 14px;");
        if (orders.isEmpty()) {
            Label noOrdersLabel = new Label("No orders yet!");
            mainBox.getChildren().add(noOrdersLabel);
        } else {
            for (Order order : orders) {
                VBox infoBox = new VBox();
                infoBox.getChildren().addAll(
                        new Label("Trip ID: " + order.getId()),
                        new Label("Trip location: " + order.getLocation()),
                        new Label("Status: " + order.getStatus()),
                        new Label()
                );
                infoBox.setStyle("-fx-font-size: 16px;");
                mainBox.getChildren().add(infoBox);
            }
        }

        ScrollPane scrollPane = new ScrollPane(mainBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(scrollPane);

        Stage stage = new Stage();
        stage.setTitle("Orders for " + customerUserManager.getCurrentUser().getFirstName());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method handles getting all the orders with their current status to the logged customer. It is then used in showOrders method.
     */
    private List<Order> getOrdersForCurrentUser() {
        List<Order> orders = new ArrayList<>();

        File file = new File("orders.db");

        if(!file.exists()) {
            System.out.println("Orders database does not exist.");
            return orders;
        }


        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:orders.db");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE customer_username = ?")) {
            stmt.setString(1, customerUserManager.getCurrentUser().getUsername());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setLocation(rs.getString("location"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    /**
     * Logs out the local out of current session.
     */
    public void LogOut(ActionEvent event) throws IOException {
        customerUserManager.setCurrentUser(null);
        Parent fourthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/mainlogin.fxml"));
        Scene fourthScene = new Scene(fourthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(fourthScene);
        currentStage.show();
        System.out.println("Log out successful.");
    }

    /**
     * Method which handles clicking on the go back button with arrow.
     */
    @FXML
    private void goBackToAfterLogin(ActionEvent event) throws IOException {
        Parent fifthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/afterLoginPageCustomer.fxml"));
        Scene fifthScene = new Scene(fifthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(fifthScene);
        currentStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (userFirstNameID != null) {
           setHelloUser(customerFirstName);
        }
    }
}