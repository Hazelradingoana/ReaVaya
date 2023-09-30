
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseConnection {
    private final Connection connection;
    private String dbUrl = "jdbc:sqlite:" + System.getProperty("user.dir") + "/Transport.db";

    public static void main(String[] args) {
        // add server initiation code here..
    }

    public DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void createTable() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS Users (" +
            "id_number INTEGER" +
            "userName TEXT," +
            "userSurname TEXT," +
            "email TEXT," +
            "phone TEXT," +
            "gender TEXT," +
            "password TEXT," +
            "PRIMARY KEY (id_number))";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }
}
