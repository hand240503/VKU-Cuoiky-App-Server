package controller;

import java.util.List;

import dao.ProductDAO;
import model.Product;

public class ProductController {

	private ProductDAO productDAO;

	public ProductController() {
		productDAO = new ProductDAO();
	}

	public List<Product> getAllProduct() {
		return productDAO.getAllProduct();
	}
}
