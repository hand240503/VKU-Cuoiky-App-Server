package service;

import dao.OrdersDAO;
import model.OrderDetail;
import model.Orders;
import model.Stock;

public class OrdersService {

	private OrdersDAO orderDAO;

	public OrdersService() {
		orderDAO = new OrdersDAO();
	}

	public int createOrder(Orders orders) {
		return orderDAO.insertOders(orders);
	}

	public int getIdOrder(int id, String date) {
		return orderDAO.selestOrder(id, date);
	}

	public int insertOrderdetail(OrderDetail detail) {
		return orderDAO.insertOrderdetails(detail);
	}

	public void insertStock(Stock stock) {
		orderDAO.insertStock(stock);
	}
}
