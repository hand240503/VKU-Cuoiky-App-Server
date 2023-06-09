package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.DbDoc;

import model.OrderDetail;
import model.Orders;
import model.Stock;
import model.UserDetailView;

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
		String sql = "INSERT INTO TA_AUT_ORDER_DETAIL (I_ID_ORDER , I_ID_PRODUCT , I_ID_PRICE , D_DATE_NEW , I_STATUS , I_ACCEPT) VALUES (?,?,?,?,?,0"
				+ ")";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, orderDetail.getId_order());
			preparedStatement.setInt(2, orderDetail.getId_product());
			preparedStatement.setInt(3, orderDetail.getId_price());
			preparedStatement.setString(4, orderDetail.getDate());
			preparedStatement.setInt(5, orderDetail.getStatus());
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

	public List<UserDetailView> getUserOrder(int id) {

		List<UserDetailView> list = new ArrayList<UserDetailView>();
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT dhct.I_ID , hh.T_NAME_PRODUCT , dv.T_LABEL , dv.I_RATIO , gia.F_VALUE , (kho.I_SUM_BEGIN - kho.I_SUM_END) soluong\r\n"
				+ "FROM TA_AUT_USER us\r\n" + "	INNER JOIN TA_AUT_ORDERS dh on us.I_ID = dh.I_ID_USER \r\n"
				+ "	INNER JOIN TA_AUT_ORDER_DETAIL dhct on dh.I_ID = dhct.I_ID_ORDER \r\n"
				+ "	INNER JOIN TA_AUT_PRICE gia on gia.I_ID = dhct.I_ID_PRICE \r\n"
				+ "	INNER JOIN TA_AUT_UNIT dv on dv.I_ID = gia.I_ID_UNIT \r\n"
				+ "	INNER JOIN TA_AUT_PRODUCT hh on hh.I_ID = dv.I_ID_PRODUCT \r\n"
				+ "	INNER JOIN TA_AUT_STOCK kho on dhct.I_ID = kho.I_ID_ODER_DETAIL  \r\n"
				+ "WHERE us.I_ID = ? and dh.I_TYPE = 1 and dhct.I_STATUS = 1\r\n" + "ORDER BY kho.I_ID  asc";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserDetailView detailView = new UserDetailView();
				detailView.setId(resultSet.getInt(1));
				detailView.setNameProduct(resultSet.getString(2));
				detailView.setUnit(resultSet.getString(3));
				detailView.setRatio(resultSet.getInt(4));
				detailView.setValue(resultSet.getDouble(5));
				detailView.setQuantity(resultSet.getInt(6));

				list.add(detailView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Stock getTonKho(int id) {
		Connection connection = DBConnect.getConnect();
		Stock stock = new Stock();

		String sql = "SELECT tkho.I_SUM_BEGIN, tkho.I_SUM_END , tkho.I_ID \r\n" + "FROM TA_AUT_STOCK tkho\r\n"
				+ "INNER JOIN TA_AUT_ORDER_DETAIL dhct ON dhct.I_ID = tkho.I_ID_ODER_DETAIL\r\n"
				+ "INNER JOIN TA_AUT_PRICE gia ON gia.I_ID = dhct.I_ID_PRICE\r\n"
				+ "INNER JOIN TA_AUT_UNIT donvi ON donvi.I_ID = gia.I_ID_UNIT\r\n"
				+ "INNER JOIN TA_AUT_PRODUCT product ON product.I_ID = donvi.I_ID_PRODUCT\r\n"
				+ "WHERE product.I_ID = (\r\n" + "    SELECT sp.I_ID\r\n" + "    FROM TA_AUT_ORDER_DETAIL dhct\r\n"
				+ "    INNER JOIN TA_AUT_PRODUCT sp ON dhct.I_ID_PRODUCT = sp.I_ID\r\n" + "    WHERE dhct.I_ID = ?\r\n"
				+ ")\r\n" + "ORDER BY tkho.I_SUM_END ASC\r\n" + "LIMIT 1;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				stock.setId(resultSet.getInt(3));
				stock.setSum_begin(resultSet.getInt(1));
				stock.setSum_end(resultSet.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock;
	}

	public int getSoLuong(int id) {
		Connection connection = DBConnect.getConnect();

		String sql = "SELECT (tkho.I_SUM_BEGIN - tkho.I_SUM_END) soluong \r\n" + "FROM TA_AUT_STOCK tkho\r\n"
				+ "INNER JOIN TA_AUT_ORDER_DETAIL dhct ON dhct.I_ID = tkho.I_ID_ODER_DETAIL\r\n"
				+ "INNER JOIN TA_AUT_PRICE gia ON gia.I_ID = dhct.I_ID_PRICE\r\n"
				+ "INNER JOIN TA_AUT_UNIT donvi ON donvi.I_ID = gia.I_ID_UNIT\r\n"
				+ "INNER JOIN TA_AUT_PRODUCT product ON product.I_ID = donvi.I_ID_PRODUCT\r\n"
				+ "WHERE dhct.I_ID = ?\r\n";
		int soLuong = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				soLuong = resultSet.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return soLuong;
	}

	public void updateStock(int id, int sumBegin, int sumEnd) {
		Connection connection = DBConnect.getConnect();
		String sql = "UPDATE TA_AUT_STOCK \r\n" + "SET I_SUM_BEGIN = ? , I_SUM_END = ?\r\n" + "WHERE I_ID = ?";
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sumBegin);
			preparedStatement.setInt(2, sumEnd);
			preparedStatement.setInt(3, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cancelOrderdetail(int id) {
		Connection connection = DBConnect.getConnect();
		String sql = "UPDATE TA_AUT_ORDER_DETAIL \r\n" + "SET I_STATUS  = 0\r\n" + "WHERE I_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int checkCancel(int id) {
		int check = 0;
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT dhct.I_ACCEPT \r\n" + "FROM TA_AUT_ORDER_DETAIL dhct\r\n" + "WHERE dhct.I_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				check = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}
}
