import java.sql.*;

public class JDBCConnectionDemo {

    public static void main(String[] args) {
        // Database URL, username and password
        String jdbcURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "javauser";
        String password = "123";

        Connection connection = null;

        try {
            // Step 1: Load JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to database successfully!");

            // Step 3: Verify connection properties
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());

            // Step 4: Query a sample table to test (optional)
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT NOW() AS current_time");
            if (rs.next()) {
                System.out.println("Current DB Time: " + rs.getString("current_time"));
            }
            rs.close();
            statement.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        } finally {
            // Step 5: Close connection
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
                e.printStackTrace();
            }
        }
    }
}
