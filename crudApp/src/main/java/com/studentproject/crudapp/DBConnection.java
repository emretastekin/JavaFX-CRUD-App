package com.studentproject.crudapp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String user="emre";
    static String password="12345";
    static String url="jdbc:mysql://localhost/crud";
    static String driver="com.mysql.cj.jdbc.Driver";

    public static Connection getCon(){
        Connection con=null;
        try {
            Class.forName(driver);
            try {
                con= DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
