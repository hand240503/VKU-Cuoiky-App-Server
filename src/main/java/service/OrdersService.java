package service;

import java.util.List;

import dao.OrdersDAO;
import model.OrderDetail;
import model.Orders;
import model.Stock;
import model.UserDetailView;

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

	public List<UserDetailView> getUserOrder(int id) {
		return orderDAO.getUserOrder(id);
	}

	public Stock getTonKho(int id) {
		return orderDAO.getTonKho(id);
	}

	public int getSoluong(int id) {
		return orderDAO.getSoLuong(id);
	}

	public void updateStock(int id, int sumBegin, int sumEnd) {
		orderDAO.updateStock(id, sumBegin, sumEnd);
	}

	public void cancelOrderdetail(int id) {
		orderDAO.cancelOrderdetail(id);
	}
}
