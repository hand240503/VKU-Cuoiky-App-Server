package model;

public class Orders {
	private int id;
	private int id_order_detail;
	private double total;
	private String date;
	private int id_user;

	public Orders() {

	}

	public Orders(int id, int id_order_detail, double total, String date, int id_user) {
		this.id = id;
		this.id_order_detail = id_order_detail;
		this.total = total;
		this.date = date;
		this.id_user = id_user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_order_detail() {
		return id_order_detail;
	}

	public void setId_order_detail(int id_order_detail) {
		this.id_order_detail = id_order_detail;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

}
