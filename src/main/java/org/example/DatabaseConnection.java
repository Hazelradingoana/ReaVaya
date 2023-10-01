package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseConnection {
    public static Connection connection = null;
    private String dbUrl = "jdbc:sqlite:" + System.getProperty("user.dir") + "/transport.db";

    public static void main(String[] args) {
        // add server initiation code here..
    }

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(dbUrl);
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void createTable() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS commuters (" +
            "id_number TEXT," +
            "userName TEXT," +
            "userSurname TEXT," +
            "email TEXT," +
            "phone TEXT," +
            "gender TEXT," +
            "password TEXT," +
            "balance INTEGER," +
            "PRIMARY KEY (id_number))";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }
}
