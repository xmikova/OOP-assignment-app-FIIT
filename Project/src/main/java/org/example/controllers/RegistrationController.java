package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.objectclasses.Customer;
import org.example.objectclasses.Local;
import org.example.objectclasses.User;
import org.example.exceptions.RegistrationAndLoginExceptions;
import org.example.tools.UserManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class which handles the registration and login in the GUI.
 */
public class RegistrationController {
    private User user;
    UserManager<Customer> customerUserManager = UserManager.getInstance();
    UserManager<Local> localUserManager = UserManager.getInstance();

    boolean boolcustomer;

    @FXML
    private RadioButton isLocal;

    @FXML
    private RadioButton isCustomer;

    @FXML
    private TextField firstName;

    @FXML
    private TextField surname;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField age;

    @FXML
    private ChoiceBox<String> locationId;

    @FXML
    private CheckBox children;

    @FXML
    private CheckBox pets;

    @FXML
    private CheckBox cityCentre;

    @FXML
    private TextField pricePerDay;

    @FXML
    private TextField usernameLogin;

    @FXML
    private PasswordField passwordLogin;

    /**
     * Method that redirects user to registration form after clicking the Sign up button.
     */
    @FXML
    private void SignUpButton(ActionEvent event) throws IOException {
        try {
            Parent secondSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/registrationform.fxml"));
            Scene secondScene = new Scene(secondSceneRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(secondScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which handles registration, depending on the user registration as customer or local, in case of local, there is redirection at another part of local registration, otherwise it registers user and gets back to main page.
     */
    @FXML
    public void Registration(ActionEvent event) {
        try {
            ArrayList<User> users = User.getUsers();
            user = new User();


            if (this.firstName.getText().isEmpty() || this.surname.getText().isEmpty() || this.username.getText().isEmpty() ||  this.password.getText().isEmpty() || this.gender.getValue() == null || this.age.getText().isEmpty()) {
                throw new RegistrationAndLoginExceptions.BlankFieldsExceptionReg();

            }else {
                user.setFirstName(this.firstName.getText());
                user.setLastName(this.surname.getText());
                user.setUsername(this.username.getText());

                for (User user : users) {
                    if ((user.getUsername().equals(this.username.getText()))) {
                        throw new RegistrationAndLoginExceptions.UsernameTakenException();
                    }
                }
                user.setPassword(this.password.getText());

                if (isNumber(this.age.getText())== true){
                    user.setAge(Integer.parseInt(this.age.getText()));
                }else throw new RegistrationAndLoginExceptions.InvalidNumberInput();


                if ("Female".equals(this.gender.getValue())) {
                    user.setFemale(true);
                } else user.setFemale(false);
            }


            if (this.isCustomer.isSelected()) {
                user.setCustomer(true);
                Customer customer = new Customer(user);
                customer.registration(customer);
                System.out.println("Registration successful.");

                Parent fourthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/mainlogin.fxml"));
                Scene fourthScene = new Scene(fourthSceneRoot);
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(fourthScene);
                currentStage.show();


            } else if (this.isLocal.isSelected()) {
                user.setCustomer(false);
                Local local = new Local(user);
                localUserManager.setCurrentUser(local);

                Parent thirdSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/registrationlocal.fxml"));
                Scene thirdScene = new Scene(thirdSceneRoot);
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(thirdScene);
                currentStage.show();


            }else{
                throw new RegistrationAndLoginExceptions.NoChoiceExceptionReg();
            }
        } catch (RegistrationAndLoginExceptions.UsernameTakenException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (RegistrationAndLoginExceptions.BlankFieldsExceptionReg e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (RegistrationAndLoginExceptions.NoChoiceExceptionReg e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (RegistrationAndLoginExceptions.InvalidNumberInput e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * A method for handling further local registration.
     */
    @FXML
    public void localRegistration(ActionEvent event){
        try {
            Local local = localUserManager.getCurrentUser();
        String[] choices = {"Madrid", "Valencia", "Barcelona"};

        if (this.locationId.getValue().isEmpty() || this.pricePerDay.getText().isEmpty()){
            throw new RegistrationAndLoginExceptions.BlankFieldsExceptionRegLocal();
        }

        for (String choice : choices) {
            if (choice.equals(this.locationId.getValue())){
                local.setLocation(choice);
            }
        }

        if (isNumber(this.pricePerDay.getText())){
            local.setPricePerDay(Integer.parseInt(this.pricePerDay.getText()));
        }else throw new RegistrationAndLoginExceptions.InvalidNumberInput();


        if (this.children.isSelected()) {
            local.setChildren(true);
        } else local.setChildren(false);

        if (this.pets.isSelected()) {
            local.setPets(true);
        } else local.setPets(false);

        if (this.cityCentre.isSelected()) {
            local.setCityCentre(true);
        } else local.setCityCentre(false);

        try {local.setPricePerDay(Integer.parseInt(this.pricePerDay.getText()));}catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText("Input valid number");
            alert.showAndWait();
        }

        local.registration(local);
        System.out.println("Registration successful.");

        Parent fourthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/mainlogin.fxml"));
        Scene fourthScene = new Scene(fourthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(fourthScene);
        currentStage.show();

        } catch (RegistrationAndLoginExceptions.BlankFieldsExceptionRegLocal e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (RegistrationAndLoginExceptions.InvalidNumberInput e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText("Input valid number");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration error");
            alert.setHeaderText("Registration error");
            alert.setContentText("Error loading main login page");
            alert.showAndWait();
        }
    }

    /**
     * A method which handles logging in of user and redirect them to their account based on whether they are customer or local.
     */
    @FXML
    public void LogInButton(ActionEvent event) {
        ArrayList<User> users = User.getUsers();

        boolcustomer = getUserType(users, this.usernameLogin.getText());

        try {
            if (this.usernameLogin.getText().isEmpty() || this.passwordLogin.getText().isEmpty()) {
                throw new RegistrationAndLoginExceptions.BlankFieldsExceptionLogin();
            }

            User matchingUser = null;
            for (User user : users) {
                if (user.getUsername().equals(this.usernameLogin.getText())) {
                    matchingUser = user;
                    break;
                }
            }

            if (matchingUser == null) {
                throw new RegistrationAndLoginExceptions.InvalidLogin();
            }

            if (!this.usernameLogin.getText().isEmpty() && !this.passwordLogin.getText().isEmpty()) {
                if (User.login(this.usernameLogin.getText(), this.passwordLogin.getText()) && boolcustomer == true) {
                    Customer customer = (Customer) matchingUser;
                    customerUserManager.setCurrentUser(customer);
                    Parent fifthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/afterLoginPageCustomer.fxml"));
                    Scene fifthScene = new Scene(fifthSceneRoot);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(fifthScene);
                    currentStage.show();
                    System.out.println("Log in successful.");
                }else if (User.login(this.usernameLogin.getText(), this.passwordLogin.getText()) && boolcustomer == false){
                    Local local = (Local) matchingUser;
                    localUserManager.setCurrentUser(local);
                    Parent fifthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/afterLoginPageLocal.fxml"));
                    Scene fifthScene = new Scene(fifthSceneRoot);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(fifthScene);
                    currentStage.show();
                    System.out.println("Log in successful.");
                } else {
                    throw new RegistrationAndLoginExceptions.InvalidLogin();
                }
            }
        } catch (RegistrationAndLoginExceptions.BlankFieldsExceptionLogin e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Login error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (RegistrationAndLoginExceptions.InvalidLogin e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Login error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Login error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Method which handles clicking on the go back button with arrow.
     */
    @FXML
    private void goBackReg(ActionEvent event) throws IOException {
        Parent fourthSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/mainlogin.fxml"));
        Scene fourthScene = new Scene(fourthSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(fourthScene);
        currentStage.show();
    }

    /**
     * Method which handles clicking on the go back button with arrow.
     */
    @FXML
    private void goBackRegLocal(ActionEvent event) throws IOException {
        Parent secondSceneRoot = FXMLLoader.load(getClass().getResource("/fxml/registrationform.fxml"));
        Scene secondScene = new Scene(secondSceneRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(secondScene);
        currentStage.show();
    }

    /**
     * Accepts the rewieved order by clicking the Accept button.
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
     * Returns true if user is customer and false if local.
     */
    public boolean getUserType(ArrayList<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.isCustomer()) {
                    return true;
                }
            }
        }
        return false;
    }
}