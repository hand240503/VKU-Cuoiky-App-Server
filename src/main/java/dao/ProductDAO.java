package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	
}
