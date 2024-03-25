import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Secure {

    public static void main(String[] args) throws SQLException {
        if (args.length != 2) {
            System.out.println("Please provide username and password only");
            return;
        }
        String username = args[0];
        String password = args[1];

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jdbc_demo",
                "postgres",
                "postgres"
        );

        String queryTemplate = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(queryTemplate);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String fetchedUsername = resultSet.getString("username");
            System.out.println("Logged in as: " + fetchedUsername);
        } else {
            System.out.println("Username or password is incorrect");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}