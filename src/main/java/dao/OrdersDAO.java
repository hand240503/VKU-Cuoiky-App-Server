package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.OrderDetail;
import model.Orders;

public class OrdersDAO {

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

	public int selestOrder(int idUser) {
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT dh.I_ID \r\n" + "FROM  TA_AUT_ORDERS dh \r\n" + "WHERE I_ID_USER = ? AND I_TYPE = 1\r\n"
				+ "ORDER BY dh.I_ID DESC  \r\n" + "LIMIT 1";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUser);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			int idOrder = resultSet.getInt("I_ID");

			return idOrder;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void insertOrderdetails(OrderDetail orderDetail) {
		Connection connection = DBConnect.getConnect();
		String sql = "INSERT INTO TA_AUT_ORDER_DETAIL (I_ID_ORDER , I_ID_PRODUCT , I_ID_PRICE , D_DATE_NEW) VALUES (?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderDetail.getId_order());
			preparedStatement.setInt(2, orderDetail.getId_product());
			preparedStatement.setInt(3, orderDetail.getId_price());
			preparedStatement.setString(4, orderDetail.getDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
