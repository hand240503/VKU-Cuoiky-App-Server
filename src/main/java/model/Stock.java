package model;

public class Stock {
	private int id;
	private int id_order_detail;
	private int sum_begin;
	private int sum_end;
	private int ratio_unit;
	private String nameProduct;
	public Stock() {

	}

	public Stock(int id, int id_order_detail, int sum_begin, int sum_end, int ratio_unit,String nameProduct) {
		this.id = id;
		this.id_order_detail = id_order_detail;
		this.sum_begin = sum_begin;
		this.sum_end = sum_end;
		this.ratio_unit = ratio_unit;
		this.nameProduct = nameProduct;
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

	public int getSum_begin() {
		return sum_begin;
	}

	public void setSum_begin(int sum_begin) {
		this.sum_begin = sum_begin;
	}

	public int getSum_end() {
		return sum_end;
	}

	public void setSum_end(int sum_end) {
		this.sum_end = sum_end;
	}

	public int getRatio_unit() {
		return ratio_unit;
	}

	public void setRatio_unit(int ratio_unit) {
		this.ratio_unit = ratio_unit;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	
}
