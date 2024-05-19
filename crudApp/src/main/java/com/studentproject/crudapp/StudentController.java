package com.studentproject.crudapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tCourse;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tID;

    @FXML
    private TextField tLastName;

    @FXML
    private TableView<Student> table;

    @FXML
    void clearField(ActionEvent event) {

    }

    @FXML
    void createStudent(ActionEvent event) {

    }

    @FXML
    void deleteStudent(ActionEvent event) {

    }

    @FXML
    void updateStudent(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

