package database_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO implements ServiceDAI{

    int idNumber; // south african id number
    String userName;
    String userSurname;
    String email;
    String phoneNumber;
    String gender;
    String password;

    
    public ServiceDAO(
        int idNumber,
        String userName,
        String userSurname,
        String email,
        String phoneNumber,
        String gender,
        String password
    ){
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
    }


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

        int id_number = result.getInt("id_number");
        String userName = result.getString("userName");
        String userSurname = result.getString("userSurname");
        String email = result.getString("email");
        String phone = result.getString("phoneNumber");
        String gender = result.getString("gender");
        String password = result.getString("password");

        return new ServiceDAO(id_number, userName, userSurname, email, phone, gender, password);
    }


    @Override
    public void addUser(Connection connection) throws SQLException {
        String insert = "INSERT INTO Users(id_number, userName, userSurname, email, phone, gender, password) VALUES(?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(insert);
        
        statement.setInt(1, this.idNumber);
        statement.setString(2, this.userName);
        statement.setString(3, this.userSurname);
        statement.setString(4, this.email);
        statement.setString(5, this.phoneNumber);
        statement.setString(6, this.gender);
        statement.setString(7, this.password);

        statement.executeUpdate();
    }
}