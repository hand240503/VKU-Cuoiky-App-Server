package service;

import java.util.List;

import dao.ProductDAO;
import model.AdminProduct;
import model.DetailView;
import model.Product;
import model.Product_Price_Views;
import model.Stock;

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

	public DetailView getView(int id) {
		return productDAO.getDetailView(id);
	}

	public List<Stock> getStock() {
		return productDAO.getStock();
	}

	public List<AdminProduct> getAdminProducts() {
		return productDAO.getAdminProducts();
	}

	public List<AdminProduct> getNoneAccept() {
		return productDAO.getNoneAccept();
	}

	public int acceptOrder(int id) {
		return productDAO.acceptOrder(id);
	}

}
