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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    public TextField tname;
    public PasswordField tpass;
    public Button btnregister;
    public Button btnBack;
    public Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnregister.setOnAction(actionEvent -> register(actionEvent));
        btnBack.setOnAction(actionEvent -> goBack(actionEvent));
        btnExit.setOnAction(actionEvent -> exit(actionEvent));

    }

    public void register(ActionEvent event) {
        String username = tname.getText();
        String password = tpass.getText();

        Connection con = DBConnection.getCon2();
        PreparedStatement st = null;

        try {
            // Yeni kullanıcıyı veritabanına ekle
            st = con.prepareStatement("INSERT INTO users (USERNAME, PASSWORD) VALUES (?, ?)");
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();

            // Kullanıcı ekledikten sonra bir bilgi mesajı göster
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Signup Successfully", ButtonType.OK);
            alert.showAndWait();

            // Mevcut pencereyi kapat ve giriş ekranına geri dön
            ((Node) (event.getSource())).getScene().getWindow().hide();

            // Giriş ekranını yeniden aç
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Mevcut pencereyi kapat
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
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
