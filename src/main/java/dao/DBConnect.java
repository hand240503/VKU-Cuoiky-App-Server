package dao;

import java.sql.*;

public class DBConnect {
	private static java.sql.Connection connect = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static String url = "jdbc:mysql://localhost:3306/NDH_APP";
	private static String user = "root", pass = "";

    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return connect = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
