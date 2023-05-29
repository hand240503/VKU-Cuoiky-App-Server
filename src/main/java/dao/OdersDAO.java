package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Orders;

public class OdersDAO {

	private Statement statement = null;
	private ResultSet resultSet = null;

	public void insertOders(Orders orders) {
		Connection connection = DBConnect.getConnect();
		String sql = "INSERT INTO TA_AUT_ORDERS (D_DATE_NEW,I_ID_USER,I_TYPE) values(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orders.getDate());
			preparedStatement.setInt(2, orders.getId_user());
			preparedStatement.setInt(3, orders.getType());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
