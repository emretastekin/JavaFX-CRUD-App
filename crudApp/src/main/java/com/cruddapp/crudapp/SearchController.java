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
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    public TextField tBookID;
    public Button btnBook;
    public Button btnBack;
    public Button btnExit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBook.setOnAction(actionEvent -> searchBookById(actionEvent));
        btnBack.setOnAction(actionEvent -> goBack(actionEvent));
        btnExit.setOnAction(actionEvent -> exit(actionEvent));


    }

    @FXML
    void searchBookById(ActionEvent event) {
        int bookId = Integer.parseInt(tBookID.getText());

        Connection con = DBConnection.getCon();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT * FROM students WHERE id = ?");
            st.setInt(1, bookId);
            rs = st.executeQuery();

            if (rs.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/bookInfo.fxml"));
                Parent root = loader.load();

                BookInfoController controller = loader.getController();
                controller.setBookInfo(rs.getString("BookName"), rs.getString("Author"), rs.getString("PAGENUMBER"), rs.getString("Price"));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not Found Book ID", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
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





}
