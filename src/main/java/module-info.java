module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.fasterxml.jackson.databind;

    opens hangman to javafx.fxml;
    exports hangman;
}