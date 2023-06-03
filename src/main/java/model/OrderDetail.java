package model;

public class OrderDetail {

	private int id;
	private int id_order;
	private int id_product;
	private int id_price;
	private String date;
	
	public OrderDetail() {
		
	}

	public OrderDetail(int id, int id_order, int id_product, int id_price, String date) {
		this.id = id;
		this.id_order = id_order;
		this.id_product = id_product;
		this.id_price = id_price;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_order() {
		return id_order;
	}

	public void setId_order(int id_order) {
		this.id_order = id_order;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public int getId_price() {
		return id_price;
	}

	public void setId_price(int id_price) {
		this.id_price = id_price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	
}
