package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.OrderDetail;
import model.Orders;
import model.Stock;

public class OrdersDAO {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private int idOrder = 0;
	private int idOrderDetail = 0;

	public int insertOders(Orders orders) {
		Connection connection = DBConnect.getConnect();
		String sql = "INSERT INTO TA_AUT_ORDERS (D_DATE_NEW,I_ID_USER,I_TYPE) values(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, orders.getDate());
			preparedStatement.setInt(2, orders.getId_user());
			preparedStatement.setInt(3, orders.getType());
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				idOrder = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idOrder;
	}

	public int selestOrder(int idUser, String date) {
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT dh.I_ID \r\n" + "FROM  TA_AUT_ORDERS dh \r\n"
				+ "WHERE I_ID_USER = ? AND I_TYPE = 1 AND D_DATE_NEW = ?" + "ORDER BY dh.I_ID DESC  \r\n" + "LIMIT 1";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUser);
			preparedStatement.setString(2, date);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int idOrder = resultSet.getInt("I_ID");
				return idOrder;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int insertOrderdetails(OrderDetail orderDetail) {
		Connection connection = DBConnect.getConnect();
		String sql = "INSERT INTO TA_AUT_ORDER_DETAIL (I_ID_ORDER , I_ID_PRODUCT , I_ID_PRICE , D_DATE_NEW) VALUES (?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, orderDetail.getId_order());
			preparedStatement.setInt(2, orderDetail.getId_product());
			preparedStatement.setInt(3, orderDetail.getId_price());
			preparedStatement.setString(4, orderDetail.getDate());
			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				idOrderDetail = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idOrderDetail;
	}

	public void insertStock(Stock stock) {
		Connection connection = DBConnect.getConnect();
		String sql = "insert into TA_AUT_STOCK (I_ID_ODER_DETAIL,I_SUM_BEGIN,I_SUM_END,I_RATIO_UNIT) value (?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, stock.getId_order_detail());
			statement.setInt(2, stock.getSum_begin());
			statement.setInt(3, stock.getSum_end());
			statement.setInt(4, stock.getRatio_unit());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
