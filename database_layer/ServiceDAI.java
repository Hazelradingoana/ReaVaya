package database_layer;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * This describes the general structure of the data access
 * objects that act as an ORM for interaction with the database
 */
public interface ServiceDAI{
    /*
     * This is to check wether a user is already registered and their details
     * exist in the database
     */
    boolean existingUser(Connection connection, int id) throws SQLException;

    ServiceDAO getUserInfo(Connection connection, int id) throws SQLException;
    
    void addUser(Connection connection) throws SQLException;

    // void updateUser() throws SQLException;

    // void deleteUser() throws SQLException;

    // ServiceDAO getUserById() throws SQLException;

    // List<ServiceDAO> getAllUsers() throws SQLException;
}