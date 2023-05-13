module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires com.google.gson;

    opens org.example.controllers to com.google.gson, javafx.fxml;
    exports org.example.controllers;
    exports org.example.objectclasses;
    opens org.example.objectclasses to com.google.gson, javafx.fxml;
    exports org.example.exceptions;
    opens org.example.exceptions to com.google.gson, javafx.fxml;
    exports org.example.tools;
    opens org.example.tools to com.google.gson, javafx.fxml;
    opens org.example.app to com.google.gson, javafx.fxml;
    exports org.example.app;
}
