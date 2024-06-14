module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.fasterxml.jackson.databind;

    opens hangman to javafx.fxml;
    exports hangman;
    exports hangman.Model.DTOs;
    opens hangman.Model.DTOs to javafx.fxml;
    exports hangman.Model.Services;
    opens hangman.Model.Services to javafx.fxml;
    exports hangman.Model.Database;
    opens hangman.Model.Database to javafx.fxml;
}