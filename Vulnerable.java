import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Vulnerable {

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

        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            String fetchedUsername = resultSet.getString("username");
            System.out.println("Logged in as: " + fetchedUsername);
        } else {
            System.out.println("Username or password is incorrect");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
