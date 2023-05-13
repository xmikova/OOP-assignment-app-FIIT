package org.example.tools;

import javafx.event.ActionEvent;

import java.io.IOException;

public interface AfterLoginScreen {
     void LogOut(ActionEvent event) throws IOException;
     void setHelloUser(String userFirstName);
}
