module com.cruddapp.crudapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.cruddapp.crudapp to javafx.fxml;
    exports com.cruddapp.crudapp;
}