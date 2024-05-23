package com.cruddapp.crudapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Login sayfasını yükle
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Parent loginParent = loginLoader.load();
        Scene loginScene = new Scene(loginParent);

        // Login Controller'ına erişim sağla
        LoginController loginController = loginLoader.getController();
        loginController.setMainApp(this);

        // Login sahnesini göster
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // İlk sayfayı yüklemek için metot
    public void showFirstPage(Stage primaryStage) throws Exception {
        // İlk sayfayı yükle
        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("/Fxml/Students.fxml"));
        Parent firstParent = firstLoader.load();
        Scene firstScene = new Scene(firstParent);

        // İlk sahneyi göster
        primaryStage.setTitle("Students");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

