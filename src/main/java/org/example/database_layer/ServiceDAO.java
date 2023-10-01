package org.example.database_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO implements ServiceDAI {

    String idNumber; // south african id number
    int balance;
    String userName;
    String userSurname;
    String email;
    String phoneNumber;
    String gender;
    String password;

    
    public ServiceDAO(
        String idNumber,
        String userName,
        String userSurname,
        String email,
        String phoneNumber,
        String gender,
        String password,
        int balance
    ){
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
        this.balance = balance;
    }

    //TODO: METHODS NEED TO BE UPDATED
    @Override
    public boolean existingUser(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE id_number = ?");
        statement.setInt(1, id);
        int result = statement.executeUpdate(); // returns 0 if no data was returned
        return result == 1;
    }


    @Override
    public ServiceDAO getUserInfo(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE id_number = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        String id_number = result.getString("id_number");
        String userName = result.getString("userName");
        String userSurname = result.getString("userSurname");
        String email = result.getString("email");
        String phone = result.getString("phoneNumber");
        String gender = result.getString("gender");
        String password = result.getString("password");
        int balance = result.getInt("balance");

        return new ServiceDAO(id_number, userName, userSurname, email, phone, gender, password, balance);
    }


    @Override
    public void addUser(Connection connection) throws SQLException {
        String insert = "INSERT INTO Users(id_number, userName, userSurname, email, phone, gender, password, balance) VALUES(?,?,?,?,?,?,?, ?);";
        PreparedStatement statement = connection.prepareStatement(insert);
        
        statement.setString(1, this.idNumber);
        statement.setString(2, this.userName);
        statement.setString(3, this.userSurname);
        statement.setString(4, this.email);
        statement.setString(5, this.phoneNumber);
        statement.setString(6, this.gender);
        statement.setString(7, this.password);
        statement.setInt(8, this.balance);

        statement.executeUpdate();
    }
}