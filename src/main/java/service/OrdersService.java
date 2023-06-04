package service;

import dao.OrdersDAO;
import model.OrderDetail;
import model.Orders;

public class OrdersService {

	private OrdersDAO orderDAO;

	public OrdersService() {
		orderDAO = new OrdersDAO();
	}

	public void createOrder(Orders orders) {
		orderDAO.insertOders(orders);
	}

	public int getIdOrder(int id) {
		return orderDAO.selestOrder(id);
	}

	public void insertOrderdetail(OrderDetail detail) {
		orderDAO.insertOrderdetails(detail);
	}
}
