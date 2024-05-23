package com.cruddapp.crudapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String user="root";
    static String password="12345";
    static String url="jdbc:mysql://localhost/crud";
    static String url2="jdbc:mysql://localhost/login";

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

    public static Connection getCon2(){
        Connection con=null;
        try {
            Class.forName(driver);
            try {
                con= DriverManager.getConnection(url2,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
