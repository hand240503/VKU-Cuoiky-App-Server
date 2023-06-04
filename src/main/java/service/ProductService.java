package service;

import java.util.List;

import dao.ProductDAO;
import model.Product;
import model.Product_Price_Views;

public class ProductService {

	private ProductDAO productDAO;

	public ProductService() {
		productDAO = new ProductDAO();
	}

	public List<Product_Price_Views> getAllProduct() {
		return productDAO.getAllProduct();
	}

	public int getSumEnd(int id) {
		return productDAO.getSumEnd(id);
	}
}
