package com.cruddapp.crudapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookInfoController implements Initializable {

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblPageNumber;

    @FXML
    private Label lblPrice;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnExit;


    public void setBookInfo(String bookName, String author, String pageNumber, String price) {
        lblBookName.setText(bookName);
        lblAuthor.setText(author);
        lblPageNumber.setText(pageNumber);
        lblPrice.setText(price);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBack.setOnAction(actionEvent -> goBack(actionEvent));
        btnExit.setOnAction(actionEvent -> exit(actionEvent));


    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/search.fxml"));
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
