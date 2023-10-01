package org.example;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class RegistrationHandler  {

    public void handle(Connection connection, Map<String, List<String>> data) throws IOException {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO commuters (id_number, userName, userSurname, email, phone, gender, password, balance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data.get("identity").get(0));
            preparedStatement.setString(2, data.get("first-name").get(0));
            preparedStatement.setString(3, data.get("last-name").get(0));
            preparedStatement.setString(4, data.get("email").get(0));
            preparedStatement.setString(5, data.get("phone").get(0));;
            preparedStatement.setString(6, data.get("gender").get(0));
            preparedStatement.setString(7, data.get("password").get(0));
            preparedStatement.setInt(8, 0);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}