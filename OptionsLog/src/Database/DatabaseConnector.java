package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String JDBC = "jdbc:mysql://127.0.0.1:3306/OptionsLog";
    private static final String MySQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;

    private static final String user = "root";
    private static final String password = "";

    public static Connection startConnection(){
        try {
            Class.forName(MySQLJDBCDriver);
            connection = (Connection) DriverManager.getConnection(JDBC, user, password);
            Query.setConnection(connection);
        } catch (ClassNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
