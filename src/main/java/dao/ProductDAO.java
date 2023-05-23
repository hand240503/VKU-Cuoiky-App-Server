package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {

	private Statement statement = null;
	private ResultSet resultSet = null;

	public List<Product> getAllProduct() {

		List<Product> products = new ArrayList<Product>();
		Connection connection = DBConnect.getConnect();
		String sql = "select * from TA_AUT_PRODUCT";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("I_ID"));
				product.setProductName(resultSet.getString("T_NAME_PRODUCT").trim());
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
