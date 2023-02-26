package org.example.utils;

import java.sql.*;

/**
 * @author Jozef
 */
public class DataSource {
    private Connection cnx;
    private String user = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/cs-workshop-2023";
    private static DataSource instance;

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
