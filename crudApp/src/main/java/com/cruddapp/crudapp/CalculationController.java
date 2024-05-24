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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CalculationController implements Initializable {

    public Button btnBack;
    public Button btnExit;
    public Button btnTotal;
    public Button btnUnit;
    public TextField tId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBack.setOnAction(actionEvent -> goBack(actionEvent));
        btnExit.setOnAction(actionEvent -> exit(actionEvent));
        btnTotal.setOnAction(actionEvent -> calculateTotalPrice(actionEvent));
        btnUnit.setOnAction(actionEvent -> calculateUnitPrice(actionEvent));
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/students.fxml"));
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

    @FXML
    void calculateTotalPrice(ActionEvent event) {
        int bookId = Integer.parseInt(tId.getText());
        Connection con = DBConnection.getCon();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT Price, BookCount FROM students WHERE id = ?");
            st.setInt(1, bookId);
            rs = st.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("Price");
                int count = rs.getInt("BookCount");
                double totalPrice = price * count;

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Total Price: " + totalPrice+" TL", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not Found Book ID", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void calculateUnitPrice(ActionEvent event) {
        int bookId = Integer.parseInt(tId.getText());
        Connection con = DBConnection.getCon();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT Price, BookCount FROM students WHERE id = ?");
            st.setInt(1, bookId);
            rs = st.executeQuery();

            if (rs.next()) {
                double totalPrice = rs.getDouble("Price");
                int bookCount = rs.getInt("BookCount");

                if (bookCount != 0) {
                    double unitPrice = totalPrice / bookCount;

                    DecimalFormat df = new DecimalFormat("#0.00");
                    String formattedUnitPrice = df.format(unitPrice);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Unit Price: " + formattedUnitPrice+"TL", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Book count is zero, cannot calculate unit price", ButtonType.OK);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not Found Book ID", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
