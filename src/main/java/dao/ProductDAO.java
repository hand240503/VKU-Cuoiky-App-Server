package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.AdminProduct;
import model.DetailView;
import model.Product;
import model.Product_Price_Views;
import model.Stock;

public class ProductDAO {

	private Statement statement = null;
	private ResultSet resultSet = null;

	public List<Product_Price_Views> getAllProduct() {

		List<Product_Price_Views> Product_Price_Views = new ArrayList<Product_Price_Views>();
		Connection connection = DBConnect.getConnect();
		String sql = ""
				+ "SELECT taproduct.I_ID , taproduct.T_NAME_PRODUCT  , taunit.T_LABEL , taunit.I_RATIO ,taprice.I_ID ,taprice.F_VALUE \r\n"
				+ "FROM TA_AUT_PRICE taprice\r\n"
				+ "	inner join TA_AUT_UNIT taunit 		on taprice.I_ID_UNIT  	= taunit.I_ID \r\n"
				+ "	inner join TA_AUT_PRODUCT taproduct on taproduct.I_ID 		= taunit.I_ID_PRODUCT  \r\n"
				+ "WHERE taprice.I_TYPE = 1";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Product_Price_Views pViews = new Product_Price_Views();
				pViews.setIdProduct(resultSet.getInt("I_ID"));
				pViews.setNameProduct(resultSet.getString("T_NAME_PRODUCT"));
				pViews.setNameUnit(resultSet.getString("T_LABEL"));
				pViews.setRatio(resultSet.getInt("I_RATIO"));
				pViews.setIdPrice(resultSet.getInt(5));
				pViews.setPrice(resultSet.getDouble("F_VALUE"));

				Product_Price_Views.add(pViews);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Product_Price_Views;
	}

	public int getSumEnd(int idProduct) {
		Connection connection = DBConnect.getConnect();
		String sql = "" + "select tkho.I_SUM_END \r\n" + "from TA_AUT_STOCK tkho\r\n"
				+ "	inner join TA_AUT_ORDER_DETAIL 	dhct 		on dhct.I_ID 		= 	tkho.I_ID_ODER_DETAIL \r\n"
				+ "	inner join TA_AUT_PRICE 		gia 		on gia.I_ID 		= 	dhct.I_ID_PRICE \r\n"
				+ "	inner join TA_AUT_UNIT 			donvi 		on donvi.I_ID  		= 	gia.I_ID_UNIT \r\n"
				+ "	inner join TA_AUT_PRODUCT 		product 	on product.I_ID  	= 	donvi.I_ID_PRODUCT \r\n"
				+ "where product.I_ID = ? \r\n" + "ORDER by tkho.I_SUM_END asc\r\n" + "limit 1";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idProduct);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int sumEnd = resultSet.getInt("I_SUM_END");
				return sumEnd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public DetailView getDetailView(int id) {
		Connection connection = DBConnect.getConnect();
		String sql = "" + "Select tapro.I_ID , tapro.T_NAME_PRODUCT ,taunit.T_LABEL , taunit.I_RATIO , gia.F_VALUE \r\n"
				+ "from TA_AUT_PRICE gia\r\n" + "	inner join TA_AUT_UNIT taunit on taunit.I_ID = gia.I_ID_UNIT \r\n"
				+ "	inner join TA_AUT_PRODUCT tapro on taunit.I_ID_PRODUCT  = tapro.I_ID \r\n"
				+ "where gia.I_ID = ? and gia.I_TYPE = 1";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DetailView detailView = new DetailView();
				detailView.setIdproduct(resultSet.getInt("I_ID"));
				detailView.setNameProduct(resultSet.getString("T_NAME_PRODUCT"));
				detailView.setNameUnit(resultSet.getString("T_LABEL"));
				detailView.setRatio(resultSet.getInt("I_RATIO"));
				detailView.setValue(resultSet.getDouble("F_VALUE"));

				return detailView;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Stock> getStock() {
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT sp.I_ID , sp.T_NAME_PRODUCT , tk.I_SUM_END ,tk.I_RATIO_UNIT \r\n" + "	FROM(\r\n"
				+ "		SELECT MAX(kho.I_ID) maxIdHH , hh.I_ID , dv.T_LABEL \r\n"
				+ "			FROM TA_AUT_STOCK kho\r\n"
				+ "				INNER JOIN TA_AUT_ORDER_DETAIL dhct on dhct.I_ID = kho.I_ID_ODER_DETAIL \r\n"
				+ "				INNER JOIN TA_AUT_PRICE gia on dhct.I_ID_PRICE = gia.I_ID \r\n"
				+ "				INNER JOIN TA_AUT_UNIT dv on gia.I_ID_UNIT = dv.I_ID \r\n"
				+ "				INNER JOIN TA_AUT_PRODUCT hh on dv.I_ID_PRODUCT = hh.I_ID \r\n"
				+ "		GROUP BY hh.I_ID \r\n" + ") a\r\n"
				+ "		INNER JOIN TA_AUT_STOCK tk on tk.I_ID = a.maxIdHH\r\n"
				+ "		INNER JOIN TA_AUT_PRODUCT sp on sp.I_ID = a.I_ID";
		List<Stock> listStock = new ArrayList<Stock>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Stock stock = new Stock();
				stock.setId(resultSet.getInt(1));
				stock.setNameProduct(resultSet.getString(2));
				stock.setSum_end(resultSet.getInt(3));
				stock.setRatio_unit(resultSet.getInt(4));

				listStock.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStock;
	}

	public List<AdminProduct> getAdminProducts() {
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT  dhct.I_ID , us.I_ID  , hh.T_NAME_PRODUCT , (kho.I_SUM_BEGIN - kho.I_SUM_END)I_QUANTITY , dv.T_LABEL  , gia.F_VALUE , dv.I_RATIO \r\n"
				+ "	FROM TA_AUT_ORDER_DETAIL dhct\r\n"
				+ "		INNER JOIN TA_AUT_ORDERS dh on dh.I_ID = dhct.I_ID_ORDER \r\n"
				+ "		INNER JOIN TA_AUT_PRICE gia on  gia.I_ID = dhct.I_ID_PRICE \r\n"
				+ "		INNER JOIN TA_AUT_PRODUCT hh on hh.I_ID = dhct.I_ID_PRODUCT \r\n"
				+ "		INNER JOIN TA_AUT_USER us on dh.I_ID_USER = us.I_ID \r\n"
				+ "		INNER JOIN TA_AUT_STOCK kho on kho.I_ID_ODER_DETAIL = dhct.I_ID \r\n"
				+ "		INNER JOIN TA_AUT_UNIT dv on dv.I_ID = gia.I_ID_UNIT \r\n"
				+ "	WHERE dhct.I_ACCEPT = 1 and dh.I_TYPE = 1 and dhct.I_STATUS = 1";
		List<AdminProduct> adminProducts = new ArrayList<AdminProduct>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				AdminProduct adminProduct = new AdminProduct();
				adminProduct.setIdOrderdetails(resultSet.getInt(1));
				adminProduct.setIdUser(resultSet.getInt(2));
				adminProduct.setNameProduct(resultSet.getString(3));
				adminProduct.setQuantity(resultSet.getInt(4));
				adminProduct.setNameUnit(resultSet.getString(5));
				adminProduct.setValue(resultSet.getFloat(6));
				adminProduct.setUnit(resultSet.getInt(7));
				adminProducts.add(adminProduct);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminProducts;
	}

	public List<AdminProduct> getNoneAccept() {
		Connection connection = DBConnect.getConnect();
		String sql = "SELECT  dhct.I_ID , us.I_ID  , hh.T_NAME_PRODUCT , (kho.I_SUM_BEGIN - kho.I_SUM_END)I_QUANTITY , dv.T_LABEL  , gia.F_VALUE , dv.I_RATIO \r\n"
				+ "	FROM TA_AUT_ORDER_DETAIL dhct\r\n"
				+ "		INNER JOIN TA_AUT_ORDERS dh on dh.I_ID = dhct.I_ID_ORDER \r\n"
				+ "		INNER JOIN TA_AUT_PRICE gia on  gia.I_ID = dhct.I_ID_PRICE \r\n"
				+ "		INNER JOIN TA_AUT_PRODUCT hh on hh.I_ID = dhct.I_ID_PRODUCT \r\n"
				+ "		INNER JOIN TA_AUT_USER us on dh.I_ID_USER = us.I_ID \r\n"
				+ "		INNER JOIN TA_AUT_STOCK kho on kho.I_ID_ODER_DETAIL = dhct.I_ID \r\n"
				+ "		INNER JOIN TA_AUT_UNIT dv on dv.I_ID = gia.I_ID_UNIT \r\n"
				+ "	WHERE dhct.I_ACCEPT = 0 and dh.I_TYPE = 1 and dhct.I_STATUS = 1\r\n";

		List<AdminProduct> adminProducts = new ArrayList<AdminProduct>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				AdminProduct adminProduct = new AdminProduct();
				adminProduct.setIdOrderdetails(resultSet.getInt(1));
				adminProduct.setIdUser(resultSet.getInt(2));
				adminProduct.setNameProduct(resultSet.getString(3));
				adminProduct.setQuantity(resultSet.getInt(4));
				adminProduct.setNameUnit(resultSet.getString(5));
				adminProduct.setValue(resultSet.getFloat(6));
				adminProduct.setUnit(resultSet.getInt(7));
				adminProducts.add(adminProduct);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminProducts;
	}

	public int acceptOrder(int id) {
		Connection connection = DBConnect.getConnect();
		String sql = "UPDATE TA_AUT_ORDER_DETAIL \r\n" + "SET I_ACCEPT = 1\r\n" + "WHERE I_ID  = ? and I_STATUS  =  ? ";
		int isUpdated = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, 1);
			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				isUpdated = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isUpdated;
	}
}
