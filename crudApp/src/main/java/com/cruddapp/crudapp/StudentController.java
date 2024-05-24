package com.cruddapp.crudapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    Connection con=null;
    PreparedStatement st= null;
    ResultSet rs=null;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnMath;

    @FXML
    private TextField tPrice;

    @FXML
    private TextField tPNumber;

    @FXML
    private TextField tBookName;

    @FXML
    private TextField tAuthor;

    @FXML
    private TextField tBookCount;

    @FXML
    private TableColumn<Student,String> colPrice;

    @FXML
    private TableColumn<Student,String> colPNumber;

    @FXML
    private TableColumn<Student,String> colbName;

    @FXML
    private TableColumn<Student,Integer> colid;

    @FXML
    private TableColumn<Student, String> colAuthor;

    @FXML
    private TableColumn<Student, String> colBookCount;

    @FXML
    private TableView<Student> table;
    int id=0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSearch.setOnAction(actionEvent -> searchStudents(actionEvent));
        btnExit.setOnAction(actionEvent -> exit(actionEvent));
        btnMath.setOnAction(actionEvent -> btnMathClicked(actionEvent));


        showStudents();
    }

    public ObservableList<Student> getStudents(){
        ObservableList<Student> students= FXCollections.observableArrayList();

        String query="select * from students";
        con=DBConnection.getCon();
        try {
            st=con.prepareStatement(query);
            rs=st.executeQuery();
            while(rs.next()){
                Student st=new Student();
                st.setId(rs.getInt("id"));
                st.setBookName(rs.getString("BookName"));
                st.setAuthor((rs.getString("Author")));
                st.setPagenumber(rs.getString("PAGENUMBER"));
                st.setPrice((rs.getString("Price")));
                st.setBookCount((rs.getString("BookCount")));


                students.add(st);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void showStudents(){
        ObservableList<Student> list= getStudents();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
        colbName.setCellValueFactory(new PropertyValueFactory<Student,String>("bookName"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Student,String>("author"));
        colPNumber.setCellValueFactory(new PropertyValueFactory<Student,String>("pagenumber"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Student,String>("price"));
        colBookCount.setCellValueFactory(new PropertyValueFactory<Student,String>("bookCount"));



    }

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void createStudent(ActionEvent event) {
        String insert="insert into students(BookName,Author,PAGENUMBER,Price,BookCount) values(?,?,?,?,?)";
        con=DBConnection.getCon();
        try {
            st=con.prepareStatement(insert);
            st.setString(1,tBookName.getText());
            st.setString(2,tAuthor.getText());
            st.setString(3,tPNumber.getText());
            st.setString(4,tPrice.getText());
            st.setString(5,tBookCount.getText());


            st.executeUpdate();
            clear();
            showStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void getData(MouseEvent event) {
        Student student=table.getSelectionModel().getSelectedItem();
        id=student.getId();
        tBookName.setText(student.getBookName());
        tAuthor.setText(student.getAuthor());
        tPNumber.setText(student.getPagenumber());
        tPrice.setText(student.getPrice());
        tBookCount.setText(student.getBookCount());


        btnSave.setDisable(true);
    }


    @FXML
    void deleteStudent(ActionEvent event) {
        String delete="delete from students where id=?";
        con=DBConnection.getCon();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clear(){
        tBookName.setText(null);
        tAuthor.setText(null);
        tPNumber.setText(null);
        tPrice.setText(null);
        tBookCount.setText(null);


        btnSave.setDisable(false);
    }

    @FXML
    void updateStudent(ActionEvent event) {
        String update="update students set BookName=?,Author=?,PAGENUMBER=?,Price=?,BookCount=? where id=?";
        con=DBConnection.getCon();
        try {
            st=con.prepareStatement(update);
            st.setString(1,tBookName.getText());
            st.setString(2,tAuthor.getText());
            st.setString(3,tPNumber.getText());
            st.setString(4,tPrice.getText());
            st.setString(5,tBookCount.getText());


            st.setInt(6,id);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchStudents(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/search.fxml"));
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

    void btnMathClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/calculations.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
