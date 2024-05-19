module com.studentproject.crudapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens com.studentproject.crudapp to javafx.fxml;
    exports com.studentproject.crudapp;
}