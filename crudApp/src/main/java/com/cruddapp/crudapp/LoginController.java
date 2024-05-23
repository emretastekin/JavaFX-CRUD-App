package com.cruddapp.crudapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private App mainApp;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public TextField tname;
    public PasswordField tpass;
    public Button btnlog;
    public Button btnsign;

    public Button btnExit;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnlog.setOnAction(actionEvent -> login(actionEvent));
        btnsign.setOnAction(actionEvent -> signup(actionEvent));
        btnExit.setOnAction(actionEvent -> exit(actionEvent));

    }

    public void login(ActionEvent event){
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection con= DBConnection.getCon2();

        try {
            st = con.prepareStatement("SELECT * FROM users WHERE USERNAME=? AND PASSWORD=?");
            st.setString(1, tname.getText());
            st.setString(2, tpass.getText());
            rs = st.executeQuery();
            if (rs.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/students.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Login Not Successfully", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void signup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/signup.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

}
