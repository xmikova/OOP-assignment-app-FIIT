package org.example.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.objectclasses.Local;
import org.example.objectclasses.Order;
import org.example.tools.AfterLoginScreen;
import org.example.tools.UserManager;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * A class for handling the order interaction from the local's side. It implements the Initializable interface in order to achieve displaying the Hello, (local's first name) in the initialize method.
 */
public class LocalOrderController implements Initializable, AfterLoginScreen {

    UserManager<Local> localUserManager = UserManager.getInstance();

    @FXML
    private Text localusernameFirstNameID;

    private String localFirstName = localUserManager.getCurrentUser().getFirstName();


    public void setHelloUser(String userFirstName){
        localusernameFirstNameID.setText(userFirstName);
    }

    /**
     * This method shows the orders which should be rewieved by clicking the button. It filters out only the orders where the current local fulfills the criteria, and it has not been rewieved by any other local yet.
     */
    public void ordersForReview(ActionEvent event){
        Local user = localUserManager.getCurrentUser();
        String location = user.getLocation();
        Boolean childrenB = user.getChildren();
        int children = childrenB ? 1 : 0;
        Boolean petsB = user.getPets();
        int pets = petsB ? 1 : 0;
        Boolean maleB = user.isFemale();
        int male = maleB ? 0 : 1;
        Boolean cityCentreB = user.getCityCentre();
        int cityCentre = cityCentreB ? 1 : 0;
        double maxPricePerDay = user.getPricePerDay();
        int age = user.getAge();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:orders.db");
             Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM orders WHERE "
                    + "min_age <= " + age
                    + " AND max_age >= " + age
                    + " AND male = " + male
                    + " AND location = '" + location + "'"
                    + " AND pets = " + pets
                    + " AND children = " + children
                    + " AND city_centre = " + cityCentre
                    + " AND max_price_per_day >= " + maxPricePerDay
                    + " AND status NOT IN ('Rejected by local', 'Accepted by local')";
            ResultSet rs = stmt.executeQuery(query);

            VBox mainBox = new VBox();
            mainBox.setPrefWidth(400);
            mainBox.setStyle("-fx-font-size: 14px;");

            if (!rs.next()) {
                Label noOrdersLabel = new Label("No orders to review!");
                mainBox.getChildren().add(noOrdersLabel);
                mainBox.setStyle("-fx-font-size: 16px;");

            } else {
                do {
                    String customerName = rs.getString("customer_first_name");
                    String customerUsername = rs.getString("customer_username");
                    int customerAge = rs.getInt("customer_age");
                    int tripLength = rs.getInt("length");
                    int orderId = rs.getInt("id");
                    VBox orderBox = new VBox();
                    Order order = new Order();
                    orderBox.getChildren().addAll(
                            new Label("Name: " + customerName),
                            new Label("Age: " + customerAge),
                            new Label("Length of the stay: " + tripLength)
                    );

                    Button acceptButton = new Button("Accept");
                    acceptButton.setOnAction(actionEvent -> handleAcceptButtonClick(order, orderId, age, user.getFirstName(), user.getUsername()));
                    orderBox.getChildren().add(acceptButton);

                    Button rejectButton = new Button("Reject");
                    rejectButton.setOnAction(actionEvent -> handleRejectButtonClick(order, orderId));
                    orderBox.getChildren().add(rejectButton);

                    orderBox.setStyle("-fx-font-size: 16px;");
                    mainBox.getChildren().add(orderBox);
                } while (rs.next());
            }

            mainBox.setSpacing(10);
            ScrollPane scrollPane = new ScrollPane(mainBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefViewportHeight(400);
            scrollPane.setStyle("-fx-background-color: transparent;");
            Scene scene = new Scene(scrollPane);
            Stage stage = new Stage();
            stage.setTitle("Trips to review");
            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method shows the accpeted orders by local by clicking the button so that they can see their upcoming stays.
     */
    public void acceptedOrders(ActionEvent event) {
        Local user = localUserManager.getCurrentUser();
        String location = user.getLocation();
        Boolean childrenB = user.getChildren();
        int children = childrenB ? 1 : 0;
        Boolean petsB = user.getPets();
        int pets = petsB ? 1 : 0;
        Boolean maleB = user.isFemale();
        int male = maleB ? 0 : 1;
        Boolean cityCentreB = user.getCityCentre();
        int cityCentre = cityCentreB ? 1 : 0;
        double maxPricePerDay = user.getPricePerDay();
        int age = user.getAge();
        String username = user.getUsername();


        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:orders.db");
             Statement stmt = conn.createStatement()) {
            // Retrieve orders that match the user's attributes
            String query = "SELECT * FROM orders WHERE "
                    + "min_age <= " + age
                    + " AND max_age >= " + age
                    + " AND male = " + male
                    + " AND location = '" + location + "'"
                    + " AND pets = " + pets
                    + " AND children = " + children
                    + " AND city_centre = " + cityCentre
                    + " AND max_price_per_day >= " + maxPricePerDay
                    + " AND status = 'Accepted by local'"
                    + " AND seller_username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            VBox mainBox = new VBox();
            mainBox.setPrefWidth(400);
            mainBox.setStyle("-fx-font-size: 14px;");

            if (!rs.next()) {
                Label noOrdersLabel = new Label("No accepted orders found!");
                mainBox.getChildren().add(noOrdersLabel);
                mainBox.setStyle("-fx-font-size: 16px;");

            } else {
                do {
                    String customerName = rs.getString("customer_first_name");
                    int customerAge = rs.getInt("customer_age");
                    int tripLength = rs.getInt("length");
                    int orderId = rs.getInt("id");


                    VBox orderBox = new VBox();
                    Order order = new Order();
                    Label nameLabel = new Label("Name: " + customerName);
                    nameLabel.setFont(Font.font(16));
                    Label ageLabel = new Label("Age: " + customerAge);
                    ageLabel.setFont(Font.font(16));
                    Label tripLabel = new Label("Length of the stay: " + tripLength);
                    tripLabel.setFont(Font.font(16));

                    orderBox.getChildren().addAll(
                            nameLabel,
                            ageLabel,
                            tripLabel,
                            new Label()
                    );

                    orderBox.setStyle("-fx-font-size: 16px;");
                    mainBox.getChildren().add(orderBox);
                } while (rs.next());
            }


            mainBox.setSpacing(10);
            ScrollPane scrollPane = new ScrollPane(mainBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefViewportHeight(400);
            scrollPane.setStyle("-fx-background-color: transparent;");

            Scene scene = new Scene(scrollPane);
            Stage stage = new Stage();
            stage.setTitle("Upcoming trips");
            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Rejects the reviewed order by clicking the Reject button.
     */
    public void handleRejectButtonClick(Order order, int id) {
        order.updateStatus(id, "Rejected by local");
    }

    /**
     * Accepts the rewieved order by clicking the Accept button.
     */
    public void handleAcceptButtonClick(Order order, int id, int sellerAge, String sellerFirstName, String sellerUsername) {
        order.updateLocal(id,"Accepted by local",sellerAge,sellerFirstName,sellerUsername);
        order.updateStatus(id, "Accepted by local");
    }

    /**
     * Logs out the local out of current session.
     */
    public void LogOut(ActionEvent event) throws IOException {
        localUserManager.setCurrentUser(null);
        Parent fourthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/mainlogin.fxml"));
        Scene fourthScene = new Scene(fourthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(fourthScene);
        currentStage.show();
        System.out.println("Log out successful.");
    }

    /**
     * Initialize method for displaying the Hello, name.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHelloUser(localFirstName);
    }
}
